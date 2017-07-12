package tw.com.softleader.sample.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

import tw.com.softleader.sample.drink.DPerson;
import tw.com.softleader.sample.drink.Drink;

public class BPersonServiceTest {

	private BPersonDao bpersonDao = new BPersonDao();

	@Test
	public void testCrud() {
		Collection<BPerson> persons = bpersonDao.findAll();
		int originalSize = persons.size();

		// To construct a new object(drink) and insert into database
		BPerson mike = new BPerson();
		mike.setIdno("A000000001");
		mike.setName("mike");

		Collection<Book> books = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setName("The Da Venci's Code");
		book1.setType("Perfect");
		books.add(book1);

		Book book2 = new Book();
		book2.setName("The Monalisa's Smile");
		book2.setType("scared");
		books.add(book2);

		Book book3 = new Book();
		book3.setName("Micky Mouse");
		book3.setType("Happy");
		books.add(book3);

		mike.setBooks(books);

		bpersonDao.insert(mike);
		// log.debug("{}", Mike);

		Long mikePersonId = mike.getId();

		// Try to modify the data
		BPerson mikeFromDB = bpersonDao.findOne(mikePersonId);

		Book Book4 = new Book();
		Book4.setName("java 1.8");
		Book4.setType("education");
		mikeFromDB.getBooks().add(Book4);
		bpersonDao.update(mikeFromDB);

		// To delete the drink that you construct before
		bpersonDao.delete(mikePersonId);
		assertNull(bpersonDao.findOne(mikePersonId));

		// check the final size should equals to original size
		persons = bpersonDao.findAll();
		System.out.println(persons);
		int finalSize = persons.size();

		assertEquals(originalSize, finalSize);

	}

}