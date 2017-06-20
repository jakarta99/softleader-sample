package tw.com.softleader.sample.books;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class BookServiceTest {
	private BookService bookService = new BookService();

	@Test
	public void testGetAll() {
		List<Book> books = bookService.getAll();

		assertEquals(books.get(0).getId(), 1);
		assertEquals(books.get(0).getName(), "Harry Potter");
		assertEquals(books.get(0).getType(), "magic");
		assertEquals(books.get(1).getId(), 2);
		assertEquals(books.get(1).getName(), "Angels and Demons");
		assertEquals(books.get(1).getType(), "religious");
		assertEquals(books.get(2).getId(), 3);
		assertEquals(books.get(2).getName(), "The Da Vinci Code");
		assertEquals(books.get(2).getType(), "mysterious");

	}

	@Test
	public void testInsert() {
		List<Book> books = bookService.getAll();
		Book book = new Book();
		book.setId(4);
		book.setName("Digital Fortress");
		book.setType("suspenseful");

		bookService.insert(book);

		assertEquals(books.get(3).getId(), 4);
		assertEquals(books.get(3).getName(), "Digital Fortress");
		assertEquals(books.get(3).getType(), "suspenseful");
	}

	@Test
	public void update() {
		List<Book> books = bookService.getAll();
		Book book = new Book();
		book.setId(3);
		book.setName("Inferno");
		book.setType("secared");

		bookService.update(book);

		assertEquals(books.get(2).getId(), 3);
		assertEquals(books.get(2).getName(), "Inferno");
		assertEquals(books.get(2).getType(), "secared");
	}

	@Test
	public void testDelete() {
		List<Book> books = bookService.getAll();
		bookService.delete(1);
		assertEquals(books.get(0).getId(), 1);
		assertEquals(books.get(0).getName(), "Harry Potter");
		assertEquals(books.get(0).getType(), "magic");
		assertEquals(books.get(1).getId(), 3);
		assertEquals(books.get(1).getName(), "The Da Vinci Code");
		assertEquals(books.get(1).getType(), "mysterious");

	}

}
