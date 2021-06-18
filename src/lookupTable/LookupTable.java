package lookupTable;

import java.util.Hashtable;

public class LookupTable {
	private Hashtable<String, DeviceType> table = new Hashtable<>();
	
	public void add(String name, int memoryRequrement) {
		if (!table.containsKey(name)) {
			DeviceType newDeviceType = new DeviceType(name, memoryRequrement);
			this.table.put(name, newDeviceType);
		} else {
			// Device allready in table
			// TODO: Add error (learn java erroros)
		}
	}
	
	public int getMemoryRequrimentFromName(String name) {
		if (table.containsKey(name)) {
			DeviceType t = table.get(name);
			return t.getRamRequirement();
		} else {
			//error
			return -1; // TODO: change this
		}
	}
	
	public static void main(String[] args) {
		// fast dirty test
		LookupTable tt = new LookupTable();
		
		tt.add("test1", 200);
		tt.add("test3", 100);
		
		System.out.println("Test1 " + tt.getMemoryRequrimentFromName("test1"));
		System.out.println("Test2 " + tt.getMemoryRequrimentFromName("test2"));
	}
		
}

