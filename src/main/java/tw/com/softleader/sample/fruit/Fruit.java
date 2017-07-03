package tw.com.softleader.sample.fruit;

public class Fruit {
	
	private Long id;
	
	private String name;
	
	private String color;


	@Override
	public String toString() {
		return "Drink [id=" + id + ", name=" + name + ", color=" + color + "]";
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
