package tw.com.softleader.sample.car;

import java.util.Collection;

public class JCompany {
	
	private Long id;
	
	private String name;
	
	private String englishName;
	
	private String uniformNumber;
	
	private Collection<JPerson> jPeople;
	
	@Override
	public String toString() {
		return "JCompany [id=" + id + ", name=" + name + ", englishName=" + englishName + ", uniformNumber=" + uniformNumber +  "]";
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getUniformNumber() {
		return uniformNumber;
	}

	public void setUniformNumber(String uniformNumber) {
		this.uniformNumber = uniformNumber;
	}

	public Collection<JPerson> getjPeople() {
		return jPeople;
	}

	public void setjPeople(Collection<JPerson> jPeople) {
		this.jPeople = jPeople;
	}

}
