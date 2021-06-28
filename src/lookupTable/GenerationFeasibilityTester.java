package lookupTable;

import deviceRegistryDummy.DeviceRegistry;

public class GenerationFeasibilityTester {

    private LookupTable table;
    private DeviceRegistry registry;

    public GenerationFeasibilityTester() {
        this.table = new LookupTable();
        this.registry = new DeviceRegistry();

        // Only for testing remove when integrating with the real device registry
        this.registry.populate();
    }

    public Boolean GenerationFeasibilityByDeviceID(Integer deviceID) {
        Boolean ret = false;
        return ret;
    }
}