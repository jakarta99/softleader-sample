package tw.com.softleader.sample.notebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WPersonServiceTest {

	private Logger log = LoggerFactory.getLogger(WPersonServiceTest.class);
	
	private WPersonService service = new WPersonService();
	
	private NotebookService notebookService = new NotebookService();
	
	@Test
	public void test() {
		Collection<WPerson> dbWPersons = new ArrayList<WPerson>();
		dbWPersons = service.getAll();
		int originalSize = dbWPersons.size();
		log.debug("originalSize = " + originalSize);
		
		// 新增一個人
		WPerson entity = new WPerson();
		entity.setIdno("A123456789");
		entity.setName("Bill");
		entity.setNotebooks(new ArrayList<Notebook>());
		
		// 筆電1
		Notebook notebook1 = new Notebook();
		notebook1.setBrand("ACER");
		notebook1.setSpecification("Intel i3");
		notebook1.setQuotation(new BigDecimal("30000"));
		notebook1.setWpersonId(entity.getId());
		entity.getNotebooks().add(notebook1);
		
		// 筆電2
		Notebook notebook2 = new Notebook();
		notebook2.setBrand("HP");
		notebook2.setSpecification("Intel i5");
		notebook2.setQuotation(new BigDecimal("36000"));
		notebook2.setWpersonId(entity.getId());
		entity.getNotebooks().add(notebook2);
		
		service.insert(entity);
		WPerson dbEntity = service.getOne(entity.getId());
		// 判斷寫入成功
		assertNotNull(dbEntity);
		// 判斷筆電有2筆
		assertEquals(2, dbEntity.getNotebooks().size());
		
		// 更改名字
		entity.setName("Simon");
		// 更改筆電1價格
		notebook1.setQuotation(new BigDecimal("32000"));
		
		service.update(entity);
		dbEntity = service.getOne(entity.getId());
		// 判斷筆電仍存在2筆
		assertEquals(2, dbEntity.getNotebooks().size());
		// 判斷筆電1價格變更為32000
		assertEquals(1L, dbEntity.getNotebooks().stream().filter(notebook -> {
			if(notebook.getId() == notebook1.getId() && new BigDecimal("32000").compareTo(notebook.getQuotation()) == 0) {
				return true;
			} else {
				return false;
			}
		}).count());
		// 判斷姓名變更為Simon
		assertEquals("Simon", dbEntity.getName());
		
		service.delete(entity.getId());
		dbEntity = service.getOne(entity.getId());
		// 判斷資料刪除
		assertNull(dbEntity);
		
		// 判斷人以下的2台筆電是否刪除
		Collection<Notebook> dbNotebooks = notebookService.getByWpersonId(entity.getId());
		assertTrue(dbNotebooks == null || dbNotebooks.isEmpty());
		
		dbWPersons = service.getAll();
		int finalSize = dbWPersons.size();
		log.debug("finalSize = " + finalSize);
		assertEquals(originalSize, finalSize);
	}

}
