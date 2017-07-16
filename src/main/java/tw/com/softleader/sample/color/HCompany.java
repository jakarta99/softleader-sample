package tw.com.softleader.sample.color;

import java.util.Collection;

public class HCompany {
	private Long id;
	private String name;
	private Collection<HPerson> hpersons;
	
	@Override
	public String toString() {
		return "HCompany [id=" + id + ", name=" + name + ", hpersons=" + hpersons + "]";
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
	public Collection<HPerson> getHpersons() {
		return hpersons;
	}
	public void setHpersons(Collection<HPerson> hpersons) {
		this.hpersons = hpersons;
	}
	

}
