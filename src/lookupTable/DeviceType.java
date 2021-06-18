package lookupTable;

public class DeviceType {
	public enum Compatabilety {
		YES,
		NO,
		WITH_REQUIREMENT
	}
	
	private String name = new String();
	private int ramRequriment;
	private Compatabilety compatible;
	
	private DeviceType(String name, int ramRequirement, Compatabilety compatible) {
		this.name = name;
		this.ramRequriment = ramRequirement;
		this.compatible = compatible;
	}
	
	public DeviceType(String name, int ramRequirement) {
		this(name, ramRequirement, Compatabilety.WITH_REQUIREMENT);
	}
	
	public int getRamRequirement() {
		return this.ramRequriment;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Compatabilety getCompatabilety() {
		return this.compatible;
	}
	
	
}