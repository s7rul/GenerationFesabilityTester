package lookupTable;

public class DeviceType {
	public enum Compatabilety {
		YES,
		NO,
		WITH_REQUIREMENT
	}
	
	private String name = new String();
	private Requirement requirement;
	private Compatabilety compatible;
	
	private DeviceType(String name, Requirement requirement, Compatabilety compatible) {
		this.name = name;
		this.requirement = requirement;
		this.compatible = compatible;
	}
	
	public DeviceType(String name, Requirement requirement) {
		this(name, requirement, Compatabilety.WITH_REQUIREMENT);
	}
	
	public Requirement getRequirements() {
		return this.requirement;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Compatabilety getCompatabilety() {
		return this.compatible;
	}
	
	
}