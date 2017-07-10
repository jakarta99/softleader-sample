package tw.com.softleader.sample.books;

public class Book {
	private Long id;

	private String name;

	private String type;
	

	@Override
	public String toString() {

		return "Book [id=" + id + ", name=" + name + ", type=" + type + "]";

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
