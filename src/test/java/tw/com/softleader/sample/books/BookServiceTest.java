package tw.com.softleader.sample.books;

import java.util.Collection;
import org.junit.Test;

public class BookServiceTest {

	private BookService bookService = new BookService();

	@Test
	public void testGetAll() {
		Collection<Book> books = bookService.getAll();

		for (Book book : books) {
			System.out.println(book);
		}

	}

	@Test
	public void testGetOne() {
		Book book = bookService.getOne((long) 2);
		System.out.println(book);
	}

	@Test
	public void testInsertUpdateDelete() {
		Book book1 = new Book();
		book1.setName("The Lord of the Rings");
		book1.setType("fantasy");

		bookService.insert(book1);

		Book book2 = new Book();
		book2.setId(4L);
		book2.setName("Java 8 API");
		book2.setType("educational");

		bookService.update(book2);

		bookService.delete(4L);

	}

}
