package tw.com.softleader.sample.KPerson;

import java.util.Collection;
import tw.com.softleader.sample.cloth.Cloth;


public class KCompany {
	
	
	private Long id;
	
	private String name;
	private Collection<KPerson> emps;
	@Override
	public String toString(){
		return " ID : " + id + " NAME : " + name +  " KPerson :" + emps ;
		
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

	public Collection<KPerson> getEmps() {
		return emps;
	}

	public void setEmps(Collection<KPerson> emps) {
		this.emps = emps;
	}







	
}
