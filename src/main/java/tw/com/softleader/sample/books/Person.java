package tw.com.softleader.sample.books;

public class Person {
	private Long p_id;

	private String personname;

	private String idno;

	private Long id;

	private String bookname;

	private String booktype;

	@Override
	public String toString() {

		return "Person [p_id=" + p_id + ", name=" + personname + ", idno=" + idno + ", id=" + id + ", bookname="
				+ bookname + ", booktype=" + booktype + "]";

	}

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return personname;
	}

	public void setName(String personname) {
		this.personname = personname;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

}
