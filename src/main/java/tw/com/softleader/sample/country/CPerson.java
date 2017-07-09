package tw.com.softleader.sample.country;

public class CPerson {
	private Long id;
	private String name;
	private String idno;
	private Long countryid;
	private String countryName;
	private String size;
	

	@Override
	public String toString() {
		return "Person[id= " + id + ",name= " + name + ",idno= " + idno + "]" + "Country[countryid= " + countryid
				+ ",country name= " + countryName + ",size= " + size + "]";
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

	public String getIdNo() {
		return idno;
	}

	public void setIdNo(String idno) {
		this.idno = idno;
	}

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
