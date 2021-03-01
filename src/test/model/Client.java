package test.model;

public class Client {
	private String name;
	private Priority priority;
	private String turn;
	
	public Client(String name, Priority priority) {
		this.name = name;
		this.priority = priority;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public String getTurn() {
		return turn;
	}

	@Override
	public String toString() {
		return " turno: " + turn +"\n"
				+ "Cliente: " + name + "\n"
						+ "Prioridad: " + priority.getName();
	}
}