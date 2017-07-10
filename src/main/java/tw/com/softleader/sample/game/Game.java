package tw.com.softleader.sample.game;

public class Game {
	private Long id;
	private String name;
	private String type;
	private Long pId;

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

}
