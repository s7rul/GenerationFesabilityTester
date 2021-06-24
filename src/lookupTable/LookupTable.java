package lookupTable;

import java.util.Hashtable;

public class LookupTable {
	private Hashtable<String, DeviceType> table = new Hashtable<>();
	
	public void add(String name) {
		if (!table.containsKey(name)) {
			DeviceType newDeviceType = new DeviceType(name, DeviceType.Compatabilety.YES);
			this.table.put(name, newDeviceType);
		} else {
			// Device allready in table
			// TODO: Add error (learn java erroros)
		}
	}
	
	public DeviceType getDeviceTypeFromName(String name) {
		return table.get(name);
	}
	
	public static void main(String[] args) {
		// fast dirty test
		LookupTable tt = new LookupTable();
		
		tt.add("test1");
		tt.add("test3");
		
		System.out.println("Test1 " + tt.getDeviceTypeFromName("test1"));
		System.out.println("Test2 " + tt.getDeviceTypeFromName("test2"));
	}
		
}

