package lookupTable;

import java.util.Hashtable;

public class LookupTable {
	private Hashtable<String, DeviceType> table = new Hashtable<>();
	
	public void add(String name, int memoryRequrement) {
		if (!table.contains(name)) {
			DeviceType newDeviceType = new DeviceType(name, memoryRequrement);
			this.table.put(name, newDeviceType);
		} else {
			// Device allready in table
			// TODO: Add error (learn java erroros
		}
	}
	
	public int getMemoryRequrimentFromName(String name) {
		if (table.contains(name)) {
			DeviceType t = table.get(name);
			return t.getRamRequirement();
		} else {
			//error
			return -1; // TODO: change this
		}
	}
		
}

