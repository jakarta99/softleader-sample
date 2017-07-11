package tw.com.softleader.sample.human;

public class Human {
	
	private Long id;
	
	private String name;
	
	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Human [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}

}
