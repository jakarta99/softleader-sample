package tw.com.softleader.sample.movie;

public class Movie {

	private Long id;
	private String name;
	private String price;

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", price=" + price + "]";
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}