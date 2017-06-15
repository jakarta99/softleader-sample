package tw.com.softleader.sample.books;
//import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.fail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookServiceTest {


	@Test
	public void bookServiceTest() {
		BookService bookService = new BookService();
		String[] books=bookService.getAll();
		
			assertEquals("Harry Porter", books[0]);
			assertEquals("The Da Vinci Code", books[1]);
			assertEquals("Angels & Demons", books[2]);

	}

}
