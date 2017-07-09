package tw.com.softleader.sample.books;

public class Book {
	private Long id;

	private String name;

	private String type;
	
	private Long p_id;

	@Override
	public String toString() {

		return "Book [id=" + id + ", name=" + name + ", type=" + type +", p_id=" + p_id + "]";

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

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

}
