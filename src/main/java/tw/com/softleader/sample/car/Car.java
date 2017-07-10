package tw.com.softleader.sample.car;

public class Car {
	
	private Long id;
	
	private String brand;
	
	private String name;
	
	private String color;
	
	private Long jPersonId;

	@Override
	public String toString() {
		return "Car [id=" + id + "brand=" + brand + ", name=" + name + ", color=" + color + ", jPersonId=" + jPersonId +  "]";
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getjPersonId() {
		return jPersonId;
	}

	public void setjPersonId(Long jPersonId) {
		this.jPersonId = jPersonId;
	}
	
}
