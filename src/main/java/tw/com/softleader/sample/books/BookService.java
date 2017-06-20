package tw.com.softleader.sample.books;

import java.util.ArrayList;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class BookService implements GenericService<Book> {

	private List<Book> books = new ArrayList<Book>();

	public BookService() {

		Book book1 = new Book();
		book1.setId(1);
		book1.setName("Harry Potter");
		book1.setType("magic");

		Book book2 = new Book();
		book2.setId(2);
		book2.setName("Angels and Demons");
		book2.setType("religious");

		Book book3 = new Book();
		book3.setId(3);
		book3.setName("The Da Vinci Code");
		book3.setType("mysterious");

		books.add(book1);
		books.add(book2);
		books.add(book3);
	}

	@Override
	public Book getOne(int id) {
		Book book = books.get(id);
		return book;
	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public void insert(Book data) {
		books.add(data);
	}

	@Override
	public void update(Book data) {
		books.set(data.getId() - 1, data);
	}

	@Override
	public void delete(int id) {
		books.remove(id);
	}

}
