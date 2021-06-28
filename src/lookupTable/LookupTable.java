package lookupTable;

import java.util.Hashtable;

public class LookupTable {
	private Hashtable<String, DeviceType> table = new Hashtable<>();
	
	public void add(DeviceType input) {
		if (!table.containsKey(input.getName())) {
			this.table.put(input.getName(), input);
		} else {
			// Device already in table
			// TODO: Add error (learn java errors)
		}
	}
	
	public void remove(String name) {
		this.table.remove(name);
	}
	
	public DeviceType getDeviceTypeByName(String name) {
		return table.get(name);
	}

	public void populate() {
		DeviceType t1 = new DeviceType("laptop", DeviceType.Compatabilety.YES);
		DeviceType t3 = new DeviceType("mc-123", DeviceType.Compatabilety.NO);
		DeviceType t2 = new DeviceType("raspberrypi", DeviceType.Compatabilety.WITH_REQUIREMENT);
		Requirement r1 = new Requirement("model", Requirement.TypeEnum.EQUAL, 4);
		t2.addRequirement(r1);

		this.add(t1);
		this.add(t2);
		this.add(t3);
	}
	
	public static void main(String[] args) {
		// fast dirty test
		LookupTable tt = new LookupTable();
		
		DeviceType t1 = new DeviceType("test1", DeviceType.Compatabilety.YES);
		DeviceType t2 = new DeviceType("test2", DeviceType.Compatabilety.NO);
		DeviceType t3 = new DeviceType("test2", DeviceType.Compatabilety.YES);
		DeviceType t4 = new DeviceType("test3", DeviceType.Compatabilety.WITH_REQUIREMENT);
		Requirement r1 = new Requirement("ram", Requirement.TypeEnum.EQUAL_OR_MORE, 512);
		t4.addRequirement(r1);
		
		tt.add(t1);
		tt.add(t2);
		tt.add(t3);
		tt.add(t4);
		
		System.out.println("Test1 " + tt.getDeviceTypeByName("test1"));
		System.out.println("Test2 " + tt.getDeviceTypeByName("test2"));
		System.out.println("Test3 " + tt.getDeviceTypeByName("test3"));
	}
		
}

