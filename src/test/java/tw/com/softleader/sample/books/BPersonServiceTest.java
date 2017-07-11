package tw.com.softleader.sample.books;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class BPersonServiceTest {

	private Logger log = Logger.getLogger(this.getClass());
	private BPersonService bpersonService = new BPersonService();
	private BookService bookService = new BookService();

	@Test
	public void testGetAll() {
		Collection<BPerson> personS = bpersonService.getAll();

		for (BPerson person : personS) {
			log.info("testGetAll:" + person);
		}

	}

	@Test
	public void testGetOne() {
		log.info("testGetOne:" + bpersonService.getOne(2L));
	}

	@Test
	public void testInsertUpdateDelete() {
		BPerson insertbperson = new BPerson();
		BookDao bookDao = new BookDao();
		Book book = new Book();
		Collection<Book> books = new ArrayList<Book>();
		insertbperson.setName("Mike");
		insertbperson.setIdno("A123456788");

		book = bookService.getOne(3L);
		books.add(book);
		insertbperson.setBooks(books); // 把id=3的書放進書堆再丟給mike

		bpersonService.insert(insertbperson);
		Long generatedId = insertbperson.getId();
		log.debug("testInsert book:" + bookDao.findOne(3L));
		log.debug("testInsert id:" + generatedId);

		BPerson bperson = bpersonService.getOne(generatedId);
	
		//update
		books.clear();
		book = bookService.getOne(2L);
		books.add(book);
		insertbperson.setBooks(books);     //把id=2的書丟進bperson裡
		bpersonService.update(insertbperson);
		//檢查是否修改
		bperson = bpersonService.getOne(generatedId);

		log.info("testUpdate id:" + generatedId);
		
		//delete
		bpersonService.delete(generatedId);
		bperson = bpersonService.getOne(generatedId);

		assertNull(bperson);

	}

}
