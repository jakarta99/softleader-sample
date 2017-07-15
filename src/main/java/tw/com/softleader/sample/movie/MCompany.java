package tw.com.softleader.sample.movie;

import java.util.Collection;

public class MCompany {

	private Long id;
	private String name;
	private Collection<MPerson> mperson;

	@Override
	public String toString() {
		return "Company: id= " + id + ",name= " + name + "mperson:" + mperson;
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

	public Collection<MPerson> getMperson() {
		return mperson;
	}

	public void setMperson(Collection<MPerson> mperson) {
		this.mperson = mperson;
	}

}