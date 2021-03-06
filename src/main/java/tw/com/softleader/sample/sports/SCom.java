package tw.com.softleader.sample.sports;

import java.util.Collection;

public class SCom {

	private Long id;
	private String name;
	private Collection<SPerson> sperson;

	@Override
	public String toString() {
		return "SCompany: id = " + id + ", name = " + name + ", sperson = " + sperson;
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

	public Collection<SPerson> getSperson() {
		return sperson;
	}

	public void setSperson(Collection<SPerson> sperson) {
		this.sperson = sperson;
	}
}
