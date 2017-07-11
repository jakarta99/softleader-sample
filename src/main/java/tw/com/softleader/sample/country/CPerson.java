package tw.com.softleader.sample.country;

import java.util.Collection;

public class CPerson {
	private Long id;
	private String name;
	private String idno;
	
	private Collection<Country> countries;

	@Override
	public String toString() {
		return "Person: id= " + id + ",name= " + name + ",idno= " + idno;
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

	public Collection<Country> getCountries() {
		return countries;
	}

	public void setCountries(Collection<Country> countries) {
		this.countries = countries;
	}
}
