package tw.com.softleader.sample.sports;

import java.util.Collection;

public class SPerson {
	private Long id;
	private String name;
	private String idnum;
	private Long comid;
	private Collection<Sport> sports;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SPerson [id=" + id + ", name=" + name + ", idnum=" + idnum + ", sports=" + sports + "]";
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

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public Collection<Sport> getSports() {
		return sports;
	}

	public void setSports(Collection<Sport> sports) {
		this.sports = sports;
	}

	public Long getComid() {
		return comid;
	}

	public void setComid(Long comid) {
		this.comid = comid;
	}

}
