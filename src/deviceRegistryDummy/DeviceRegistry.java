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
		Device d2 = new Device(2, "raspberrypi", d2l);

		List<Pair<String, Integer>> d3l = new LinkedList<>();
		Pair<String, Integer> x6 = new Pair<>("ram", 2000);
		Pair<String, Integer> x7 = new Pair<>("cpu_speed", 1000);
		Pair<String, Integer> x8 = new Pair<>("model", 2);
		d3l.add(x6);
		d3l.add(x7);
		d3l.add(x8);
		Device d3 = new Device(3, "raspberrypi", d3l);

		List<Pair<String, Integer>> d4l = new LinkedList<>();
		Pair<String, Integer> x9 = new Pair<>("ram", 64);
		Pair<String, Integer> x10 = new Pair<>("cpu_speed", 800);
		Pair<String, Integer> x11 = new Pair<>("rev", 2);
		d4l.add(x9);
		d4l.add(x10);
		d4l.add(x11);
		Device d4 = new Device(3, "mc-123", d4l);

		List<Pair<String, Integer>> d5l = new LinkedList<>();
		Pair<String, Integer> x12 = new Pair<>("ram", 1280000);
		Pair<String, Integer> x13 = new Pair<>("cpu_speed", 4000);
		Pair<String, Integer> x14 = new Pair<>("model", 2);
		d3l.add(x12);
		d3l.add(x13);
		d3l.add(x14);
		Device d5 = new Device(3, "big_random_server", d5l);

		this.add(d1);
		this.add(d2);
		this.add(d3);
		this.add(d4);
		this.add(d5);
	}

	@Override
	public String toString() {
		return this.table.toString();
	}

	public static void main(String[] args) {
		DeviceRegistry testRegistry = new DeviceRegistry();
		testRegistry.populate();
		System.out.println(testRegistry);
	}
}
