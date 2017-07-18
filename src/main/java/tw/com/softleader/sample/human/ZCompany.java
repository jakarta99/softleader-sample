package tw.com.softleader.sample.human;

import java.util.Collection;

public class ZCompany {

	private long id;
	
	private String type;
	
	private Collection<XPerson> members;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<XPerson> getMembers() {
		return members;
	}

	public void setMembers(Collection<XPerson> members) {
		this.members = members;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
