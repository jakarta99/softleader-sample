package tw.com.softleader.sample.books;

import static org.junit.Assert.*;

import java.util.Collection;
import org.junit.Test;

import tw.com.softleader.sample.drink.Drink;

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
		System.out.println(book1);
		Long generatedId=book1.getId();
		
		
		book1.setName("Java 8 API");
		book1.setType("educational");

		bookService.update(book1);
		
		Book entity = bookService.getOne(generatedId);
		assertEquals("Java 8 API",(entity.getName()));
		assertEquals("educational",(entity.getType()));

		bookService.delete(generatedId);

	}

}
