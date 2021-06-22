package lookupTable;

import java.util.LinkedList;
import java.util.List;
//import lookupTable.Pair;

public class DeviceType {
	public enum Compatabilety {
		YES,
		NO,
		WITH_REQUIREMENT
	}
	
	private String name = new String();
	private List<Requirement> requirements;
	private Compatabilety compatible;
	
	public DeviceType(String name, Compatabilety compatible) {
		this.name = name;
		this.compatible = compatible;
		this.requirements = new LinkedList<Requirement>();
	}
	
	public void addRequirement(Requirement x) {
		this.requirements.add(x);
	}
	
	public void removeRequirementByName(String name) {
		for (int i = 0; i < this.requirements.size(); i++) {
			if (this.requirements.get(i).name.equals(name)) {
				this.requirements.remove(i);
			}
		}
	}
	
	public List<Requirement> getRequirements() {
		return this.requirements;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Compatabilety getCompatabilety() {
		return this.compatible;
	}
	
	public Boolean validateDeviceType() {
		return (this.compatible == DeviceType.Compatabilety.YES);
	}
	
	public Boolean validateDeviceType(List<Pair<String, Integer>> values) {
		switch (this.compatible) {
		case YES:
			return true;
		case NO:
			return false;
		case WITH_REQUIREMENT:
			return this.validateRequirements(values);
		}
		// should never ever get here
		return false;
	}
	
	private Boolean validateRequirements(List<Pair<String, Integer>> values) {
		for (Requirement n: this.getRequirements()) {
			for (Pair<String, Integer> x: values) {
				if (x.key.equals(n.name)) {
					if(!n.validate(x.value)) {
						return false;
					} else {
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	
	
	public static void main(String[] args) {
		// Small simple tests
		
		DeviceType device = new DeviceType("Test", DeviceType.Compatabilety.WITH_REQUIREMENT);
		Requirement r1 = new Requirement("ram", Requirement.TypeEnum.EQUAL_OR_MORE, 2048);
		Requirement r2 = new Requirement("cpu_Hz", Requirement.TypeEnum.MORE_THEN, 1000);
		device.addRequirement(r1);
		device.addRequirement(r2);
		
		List<Requirement> rList = device.getRequirements();
		System.out.println("Printing out requirements");
		for (Requirement n: rList) {
			System.out.println(n.toString());
		}
		System.out.println("Removing r1");
		device.removeRequirementByName("ram");
		System.out.println("Printing out requirements");
		for (Requirement n: rList) {
			System.out.println(n.toString());
		}
	}
}