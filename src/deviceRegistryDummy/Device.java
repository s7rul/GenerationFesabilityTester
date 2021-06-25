package deviceRegistryDummy;

import java.util.List;

import lookupTable.Pair;

public class Device {
	private int deviceID;
	private String deviceType;
	private List<Pair<String, Integer>> metaData;
	
	public Device(int deviceID, String deviceType, List<Pair<String, Integer>> metaData) {
		this.deviceID = deviceID;
		this.deviceType = deviceType;
		this.metaData = metaData;
	}
	
	public int getDeviceID() {
		return this.deviceID;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public List<Pair<String, Integer>> getMetaData() {
		return this.metaData;
	}
}
