package deviceRegistryDummy;

import java.util.Hashtable;

public class DeviceRegistry {
	private Hashtable<Integer, Device> table = new Hashtable<>();
	
	public void add(Device input) {
		if (!table.containsKey(input.getDeviceID())) {
			this.table.put(input.getDeviceID(), input);
		} else {
			// Device already in table
			// TODO: Add error (learn java errors)
		}
	}
	
	public void remove(String name) {
		this.table.remove(name);
	}
	
	public Device getDeviceByID(int id) {
		return table.get(id);
	}
}
