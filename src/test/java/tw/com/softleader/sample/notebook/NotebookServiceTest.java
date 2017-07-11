package tw.com.softleader.sample.notebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotebookServiceTest {
	
	private Logger log = LoggerFactory.getLogger(NotebookServiceTest.class);
	
	private NotebookService notebookService = new NotebookService();

	@Test
	public void test() {
		Collection<Notebook> notebookList = notebookService.getAll();
		int originalSize = notebookList.size();
		log.debug("originalNotebookList size = " + originalSize);
		
		Notebook entity = new Notebook();
		entity.setBrand("ACER");
		entity.setSpecification("Intel i5");
		entity.setQuotation(new BigDecimal("30000"));
		notebookService.insert(entity);
		Long notebookId = entity.getId();
		Notebook dbEntity = notebookService.getOne(notebookId);
		assertNotNull(dbEntity);
		
		entity.setBrand("HP");
		entity.setQuotation(new BigDecimal("35000"));
		notebookService.update(entity);
		dbEntity = notebookService.getOne(notebookId);
		assertEquals("HP", dbEntity.getBrand());
		assertTrue(new BigDecimal("35000").compareTo(dbEntity.getQuotation()) == 0);
		
		notebookService.delete(notebookId);
		assertNull(notebookService.getOne(notebookId));
		
		notebookList = notebookService.getAll();
		int finalSize = notebookList.size();
		log.debug("finalNotebookList size = " + finalSize);
		
		assertEquals(originalSize, finalSize);
	}

}
