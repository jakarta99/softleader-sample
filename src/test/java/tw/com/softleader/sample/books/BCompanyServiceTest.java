package tw.com.softleader.sample.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

public class BCompanyServiceTest {
	private BCompanyDao bcompanyDao = new BCompanyDao();
	private Logger log = Logger.getLogger(this.getClass());

	@Test
	public void testCrud() {

		BCompany msi = new BCompany();
		msi.setName("msi");

		BPerson bperson1 = new BPerson();
		bperson1.setName("mike");
		bperson1.setIdno("A000000001");

		BPerson bperson2 = new BPerson();
		bperson2.setName("hebe");
		bperson2.setIdno("A000000002");

		Collection<Book> books1 = new ArrayList<Book>();

		Book book1 = new Book();
		book1.setName("The Da Venci's Code");
		book1.setType("Perfect");
		books1.add(book1);

		Book book2 = new Book();
		book2.setName("The Monalisa's Smile");
		book2.setType("scared");
		books1.add(book2);

		Book book3 = new Book();
		book3.setName("Micky Mouse");
		book3.setType("Happy");
		books1.add(book3);

		bperson1.setBooks(books1);

		Collection<Book> books2 = new ArrayList<Book>();
		Book book4 = new Book();
		book4.setName("JAVA 1.8 API");
		book4.setType("education");
		books2.add(book4);

		Book book5 = new Book();
		book5.setName("Servlets & JSP");
		book5.setType("well done");
		books2.add(book5);

		bperson2.setBooks(books2);

		Collection<BPerson> bpersons = new ArrayList<BPerson>();
		bpersons.add(bperson1);
		bpersons.add(bperson2);

		msi.setBpersons(bpersons);

		Iterator<BPerson> bperson = msi.getBpersons().iterator();
		while (bperson.hasNext()) {
			log.debug(bperson.next().getBooks().size());
			log.debug(bperson.next().getBooks().size());
		}
		
		log.debug(msi);
		bcompanyDao.insert(msi);
		log.debug(msi);

		Long msiCompanyId = msi.getId();
		log.debug(msiCompanyId);

		BCompany bcompany = bcompanyDao.findOne(msiCompanyId);  //列印出msi公司員工所有的書
		log.debug(bcompany);

		Book book6 = new Book();
		book6.setName("javascript");
		book6.setType("education");
		bperson2.getBooks().add(book6);  
		
		bcompanyDao.update(msi);//把hebe多加一本書"javascript"
		
		Long msiCompanyId1 = msi.getId();
		
		Collection<BCompany> bcompanies = bcompanyDao.findAll();
		log.debug(bcompanies);
		
		bcompanyDao.delete(msiCompanyId1);
		assertNull(bcompanyDao.findOne(msiCompanyId1));


		


	}
}
