package tw.com.softleader.sample.cloth;

public class Cloth {
	
	
	private Long id;
	
	private String name;
	
	private String color;
	
	private Long ownerId;
	
	@Override
	public String toString(){
		return " ID : " + id + " NAME : " + name + " COLOR :" + color ;
		
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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
