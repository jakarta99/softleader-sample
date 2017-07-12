package tw.com.softleader.sample.books;

import java.util.Collection;

public class BCompany {
	
	private Long id;
	
	private String name;
	
	private Collection<BPerson> bpersons;
	
	@Override
	public String toString() {

		return "BCompany [id=" + id + ", name=" + name + ", bperson=" + bpersons + "]";

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

	public Collection<BPerson> getBpersons() {
		return bpersons;
	}

	public void setBpersons(Collection<BPerson> bpersons) {
		this.bpersons = bpersons;
	}



}
