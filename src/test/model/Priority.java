package test.model;

public enum Priority {
	OLDER_ADULT("Adulto mayor"), PREGNACY("Embarazada"),
	NORMAL_USER("Usuario Normal");
	
	private String name;
	
	private Priority(String name) {
		this.name = name;
	}
	 
	public String getName() {
		return name;
	}
}