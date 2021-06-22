package lookupTable;



public class Requirement {
	public enum TypeEnum{
		EQUAL, MORE_THEN, LESS_THEN, EQUAL_OR_LESS, EQUAL_OR_MORE
	}
	
	public String name;
	public TypeEnum type;
	public int value;
	
	public Requirement(String name, TypeEnum type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
}
