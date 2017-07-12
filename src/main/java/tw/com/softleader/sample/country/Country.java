package tw.com.softleader.sample.country;

public class Country {
	private Long id;
	private String name;
	private String size;
	private Long p_id;

	@Override
	public String toString() {
		return "Country[id="+id+",name="+name+",size="+size+",p_id"+p_id+"]";
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
	public Long getP_ID(){
		return p_id;
	}
	
	public void setP_ID(Long p_id) {
		this.p_id = p_id;
	}

}
