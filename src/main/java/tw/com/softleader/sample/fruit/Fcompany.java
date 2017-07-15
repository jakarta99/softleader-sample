package tw.com.softleader.sample.fruit;

import java.util.Collection;

public class Fcompany {
	
	private Long id;
	
	private String name;
	
	private String uno;
	
	private Collection<Fman> fmans;

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

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public Collection<Fman> getFmans() {
		return fmans;
	}

	public void setFmans(Collection<Fman> fmans) {
		this.fmans = fmans;
	}
	
	

}
