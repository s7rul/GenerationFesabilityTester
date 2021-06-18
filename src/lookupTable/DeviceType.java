package lookupTable;

class DeviceType {
	private String name = new String();
	private int ramRequriment;
	
	public DeviceType(String name, int ramRequirement) {
		this.name = name;
		this.ramRequriment = ramRequirement;
	}
	
	public int getRamRequirement() {
		return this.ramRequriment;
	}
	
	
}