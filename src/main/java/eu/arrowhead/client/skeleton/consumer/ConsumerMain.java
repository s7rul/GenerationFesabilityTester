package eu.arrowhead.client.skeleton.consumer;

import GenerationFeasibilityTester.GenerationFeasibilityTester;
import deviceRegistryDummy.DeviceRegistry;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;

import eu.arrowhead.client.library.ArrowheadService;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.SSLProperties;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.dto.shared.OrchestrationFlags.Flag;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO.Builder;
import eu.arrowhead.common.dto.shared.OrchestrationResponseDTO;
import eu.arrowhead.common.dto.shared.OrchestrationResultDTO;
import eu.arrowhead.common.dto.shared.ServiceInterfaceResponseDTO;
import eu.arrowhead.common.dto.shared.ServiceQueryFormDTO;
import eu.arrowhead.common.exception.ArrowheadException;
import eu.arrowhead.common.exception.InvalidParameterException;
import systemRegistryDummy.SystemRegistry;

import java.io.*;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE}) //TODO: add custom packages if any
public class ConsumerMain implements ApplicationRunner {
    
    //=================================================================================================
	// members
	
    @Autowired
	private ArrowheadService arrowheadService;
    
    @Autowired
	protected SSLProperties sslProperties;
    
	private final Logger logger = LogManager.getLogger( ConsumerMain.class );
    
    //=================================================================================================
	// methods

	//------------------------------------------------------------------------------------------------
    public static void main( final String[] args ) {
    	SpringApplication.run(ConsumerMain.class, args);
    }

    //-------------------------------------------------------------------------------------------------
    @Override
	public void run(final ApplicationArguments args) throws Exception {

		SystemRegistry systemRegistry = new SystemRegistry();

    	logger.info("Request for generation gotten");

    	Long providerServiceID = null;
    	Long consumerID = 9l;

    	Long consumerDeviceID = systemRegistry.getDeviceBySystemID(consumerID);


    	logger.info("Orchestration request for deploy_jar service:");
    	final ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO.Builder(ConsumerConstants.DEPLOY_JAR_SERVICE_DEFINITION)
    																		.interfaces(getInterface())
    																		.build();
    	
		final Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest = orchestrationFormBuilder.requestedService(serviceQueryForm)
																					   .flag(Flag.MATCHMAKING, true)
																					   .flag(Flag.OVERRIDE_STORE, true)
																					   .build();
		
		//printOut(orchestrationFormRequest);
		
		final OrchestrationResponseDTO orchestrationResponse = arrowheadService.proceedOrchestration(orchestrationFormRequest);


		
		logger.info("Orchestration response:");
		printOut(orchestrationResponse);		
		
		if (orchestrationResponse == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {
			logger.info("Checking there is way to deploy translator.");

			OrchestrationResultDTO orchestrationResult = null;
			for (OrchestrationResultDTO n: orchestrationResponse.getResponse()) {
				if (systemRegistry.getDeviceBySystemID(n.getProvider().getId()) == consumerDeviceID) {
					orchestrationResult = n;
				}
			}

			if (orchestrationResult == null) {
				logger.info("No way to deploy found, aborting.");
				return;
			}

			logger.info("Estimating whether or not the interface can be run on the device.");
			GenerationFeasibilityTester tester = new GenerationFeasibilityTester();

			if (!tester.generationFeasibilityByDeviceID(consumerDeviceID)) {
				logger.info("It will probably not run, aborting.");
				return;
			}

			validateOrchestrationResult(orchestrationResult, ConsumerConstants.DEPLOY_JAR_SERVICE_DEFINITION);
			
			logger.info("Create a request:");
			final HttpMethod httpMethod = HttpMethod.valueOf(orchestrationResult.getMetadata().get(ConsumerConstants.HTTP_METHOD));
			final String token = orchestrationResult.getAuthorizationTokens() == null ? null : orchestrationResult.getAuthorizationTokens().get(getInterface());
			/*final String sr = arrowheadService.consumeServiceHTTP(String.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(ConsumerConstants.HTTP_METHOD)),
					orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
					getInterface(), token, null, new String[0]);*/

			// create payload
			File toDeploy = new File("/home/s7rul/IdeaProjects/GenerationFesabilityTester/InterfaceLightweight-1.0.jar");
			FileInputStream toDeployStream = null;
			HttpEntity entity = null;
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://" + orchestrationResult.getProvider().getAddress() + ":" + orchestrationResult.getProvider().getPort() + orchestrationResult.getServiceUri());

			try {
			    toDeployStream = new FileInputStream(toDeploy);
			    MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
			    entityBuilder.addPart("file", new InputStreamBody(toDeployStream, toDeploy.getName()));
			    entityBuilder.addTextBody("test", "tt");
			    entity = entityBuilder.build();

			    httppost.setEntity(entity);
			} catch (Exception e) {
				System.out.println(e);
			}
			CloseableHttpResponse response = client.execute(httppost);

			//final String sr = arrowheadService.consumeServiceHTTP(String.class, httpMethod,
					//orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
					//getInterface(), token, entity, new String[0]);
			logger.info("Provider response");
			//printOut(sr);

			try {
				System.out.println("#-------------------------------------------------------------#");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				EntityUtils.consume(resEntity);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				response.close();
			}

			client.close();
		}
	}
    
    private void printOut(final Object object) {
    	System.out.println(Utilities.toPrettyJson(Utilities.toJson(object)));
    }
    
    private String getInterface() {
    	return sslProperties.isSslEnabled() ? ConsumerConstants.INTERFACE_SECURE : ConsumerConstants.INTERFACE_INSECURE;
    }
    
    private void validateOrchestrationResult(final OrchestrationResultDTO orchestrationResult, final String serviceDefinitin) {
    	if (!orchestrationResult.getService().getServiceDefinition().equalsIgnoreCase(serviceDefinitin)) {
			throw new InvalidParameterException("Requested and orchestrated service definition do not match");
		}
    	
    	boolean hasValidInterface = false;
    	for (final ServiceInterfaceResponseDTO serviceInterface : orchestrationResult.getInterfaces()) {
			if (serviceInterface.getInterfaceName().equalsIgnoreCase(getInterface())) {
				hasValidInterface = true;
				break;
			}
		}
    	if (!hasValidInterface) {
    		throw new InvalidParameterException("Requested and orchestrated interface do not match");
		}
    }
}
