package tw.com.softleader.sample.books;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class BookServiceTest {
	private BookService bookService = new BookService();
	List<Book> books = bookService.getAll();

	@Test
	public void testGetOne() {
		Book book = bookService.getOne(19);
		assertEquals(book.getId(), 19);
		assertEquals(book.getName(), "The Da Vinci Code");
		assertEquals(book.getType(), "mysterious");
	}

	@Test
	public void testGetAll() {

		assertEquals(books.get(0).getId(), 1);
		assertEquals(books.get(0).getName(), "Harry Potter");
		assertEquals(books.get(0).getType(), "magic");
		assertEquals(books.get(1).getId(), 2);
		assertEquals(books.get(1).getName(), "Angels and Demons");
		assertEquals(books.get(1).getType(), "religious");
		assertEquals(books.get(2).getId(), 19);
		assertEquals(books.get(2).getName(), "The Da Vinci Code");
		assertEquals(books.get(2).getType(), "mysterious");

	}

	@Test
	public void testInsertUpdateDelete() {

		Book book1 = new Book();
		book1.setId(5);
		book1.setName("Digital Fortress");
		book1.setType("suspenseful");
		bookService.insert(book1);
		assertEquals(books.get(3).getId(), 5);
		assertEquals(books.get(3).getName(), "Digital Fortress");
		assertEquals(books.get(3).getType(), "suspenseful");
		Book book2 = new Book();
		book2.setId(19);
		book2.setName("Inferno");
		book2.setType("secared");
		bookService.update(book2);
		assertEquals(books.get(2).getId(), 19);
		assertEquals(books.get(2).getName(), "Inferno");
		assertEquals(books.get(2).getType(), "secared");
		Book book = bookService.getOne(19);
		bookService.delete(19);
		assertEquals(books.contains(book), false);

	}

}
