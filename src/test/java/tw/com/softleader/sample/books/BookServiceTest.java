package tw.com.softleader.sample.books;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class BookServiceTest {
	private BookService bookService = new BookService();

	@Test
	public void testGetAll() {
		List<Book> books = bookService.getAll();
		
		assertEquals(books.get(0).getId(), "1");
		assertEquals(books.get(0).getName(), "coffee");
		assertEquals(books.get(0).getType(), "black");
		
	}

}
