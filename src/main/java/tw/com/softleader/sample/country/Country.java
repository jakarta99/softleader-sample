package tw.com.softleader.sample.country;

public class Country {
	private Long id;
	private String name;
	private String size;

	@Override
	public String toString() {
		return "Country[id="+id+",name="+name+",size="+size+"]";
	}

	public Long getId(){
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
