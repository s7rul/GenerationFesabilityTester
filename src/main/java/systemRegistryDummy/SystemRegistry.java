package systemRegistryDummy;

import java.util.Hashtable;

public class SystemRegistry {
    private Hashtable<Integer, Integer> table = new java.util.Hashtable<>();

    public SystemRegistry() {
        this.table.put(9, 2); // consumer is on raspberry pi 4
        this.table.put(6, 2); // jar deployer on /\ (can shange look up)

        // core systems on laptop
        this.table.put(4, 1);
        this.table.put(5, 1);
        this.table.put(7, 1);
    }

    public int getDeviceBySystemID(int systemID) {
        return this.table.get(systemID);
    }
}
