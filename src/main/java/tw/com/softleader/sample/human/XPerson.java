package tw.com.softleader.sample.human;

import java.util.Collection;

public class XPerson {
	
    private Long id;
    
    private String idNo;
    
    private String name;
 
    private Collection<Human> humans;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdNo() {
		return idNo;
	}
	
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Human> getHumans() {
		return humans;
	}
	
	public void setHumans(Collection<Human> humans) {
		this.humans = humans;
	}

	@Override
	public String toString() {
		return "XPerson [id=" + id + ", idNo=" + idNo + ", name=" + name + ", humans=" + humans + "]";
	}
	
}
