package tw.com.softleader.sample.country;

import java.util.Collection;

public class CCompany {
	private Long id;
	private String name;
	
	private Collection<CPerson>cPersons;

	@Override
	public String toString() {
		return "Company: id= " + id + ",name= " + name;
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

	public Collection<CPerson> getCPersons() {
		return cPersons;
	}

	public void setCPersons(Collection<CPerson> cPersons) {
		this.cPersons = cPersons;
	}
}
