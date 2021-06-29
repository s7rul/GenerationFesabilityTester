package GenerationFeasibilityTester;

import deviceRegistryDummy.DeviceRegistry;
import deviceRegistryDummy.Device;

public class GenerationFeasibilityTester {

    private LookupTable table;
    private DeviceRegistry registry;

    public GenerationFeasibilityTester() {
        this.table = new LookupTable();
        this.registry = new DeviceRegistry();

        // Only for testing remove when integrating with the real device registry
        this.registry.populate();
        this.table.populate();
    }

    public Boolean generationFeasibilityByDeviceID(Integer deviceID) {
        // TODO: add proper error handling
        Device d = registry.getDeviceByID(deviceID);
        if (d == null) {
            System.out.println("device not in device registry");
            return false;
        }

        DeviceType type = table.getDeviceTypeByName(d.getDeviceType());
        if (type == null) {
            System.out.println("device type not in table");
            return false;
        } else {
            return type.validate(d.getMetaData());
        }
    }
}