package tw.com.softleader.sample.books;

import java.util.Collection;

public class BPerson {
	private Long id;

	private String name;

	private String idno;

	private Collection<Book> books;
	@Override
	public String toString() {

		return "Person [id=" + id + ", name=" + name + ", idno=" + idno + ", book=" + books + "]";

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

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}


	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

}
