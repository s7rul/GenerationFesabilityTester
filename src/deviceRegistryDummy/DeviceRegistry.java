package deviceRegistryDummy;

import lookupTable.Pair;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

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
	
	public void remove(Integer name) {
		this.table.remove(name);
	}
	
	public Device getDeviceByID(int id) {
		return table.get(id);
	}

	public void populate() {
		List<Pair<String, Integer>> d1l = new LinkedList<>();
		Pair<String, Integer> x1 = new Pair<>("ram", 16000);
		Pair<String, Integer> x2 = new Pair<>("cpu_speed", 3000);
		d1l.add(x1);
		d1l.add(x2);
		Device d1 = new Device(1, "laptop", d1l);

		List<Pair<String, Integer>> d2l = new LinkedList<>();
		Pair<String, Integer> x3 = new Pair<>("ram", 4000);
		Pair<String, Integer> x4 = new Pair<>("cpu_speed", 1000);
		Pair<String, Integer> x5 = new Pair<>("model", 4);
		d2l.add(x3);
		d2l.add(x4);
		d2l.add(x5);
		Device d2 = new Device(2, "rapberrypi", d2l);

		List<Pair<String, Integer>> d3l = new LinkedList<>();
		Pair<String, Integer> x6 = new Pair<>("ram", 2000);
		Pair<String, Integer> x7 = new Pair<>("cpu_speed", 1000);
		Pair<String, Integer> x8 = new Pair<>("model", 2);
		d3l.add(x6);
		d3l.add(x7);
		d3l.add(x8);
		Device d3 = new Device(2, "rapberrypi", d3l);

		this.add(d1);
		this.add(d2);
		this.add(d3);
	}
}
