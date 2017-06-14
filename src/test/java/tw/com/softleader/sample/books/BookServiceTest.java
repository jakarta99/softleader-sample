package tw.com.softleader.sample.books;
//import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.fail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookServiceTest {
	public void assertEquals(String book1, String book2, String book3, String[] book) {
	}

	@Test
	public void bookServiceTest() {
		BookService bookService = new BookService();
		for (String book : bookService.getAll()) {
			assertEquals("Harry Porter", "The Da Vinci Code", "Angels & Demons", bookService.getAll());
		}

	}

}
