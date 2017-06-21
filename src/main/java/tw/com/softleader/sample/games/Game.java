package tw.com.softleader.sample.games;

public class Game {
	private String name;
	private String type;
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setName(String Name) {
		this.name = Name;

	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}