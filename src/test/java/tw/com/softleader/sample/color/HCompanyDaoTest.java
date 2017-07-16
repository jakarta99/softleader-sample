package tw.com.softleader.sample.color;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HCompanyDaoTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	HCompanyDao dao= new HCompanyDao();
	
	@Test
	public void testCRUD() {
		
		Collection<HCompany> companys = dao.findAll();
		int originalSize = companys.size();
		
		// SET COMPANY
		HCompany insertHCompany = new HCompany();
		insertHCompany.setName("ACompany");
		
		// SET PERSON
		Collection<HPerson> insertHperson = new ArrayList<HPerson>();
		// INSERT PEOPLE ADA
		HPerson person1 = new HPerson();
		person1.setName("Ada");
		person1.setIdNo("A1111");
		
		HPerson person2 = new HPerson();
		person2.setName("Bella");
		person2.setIdNo("B1112");
		
		// SET COLOR FOR PERSON1
		Collection<Color> colors = new ArrayList<Color>();
		// INSERT COLOR Green
		Color colorGreen = new Color();
		colorGreen.setName("Green");
		colorGreen.setCode("#00FF00");
		colors.add(colorGreen);
		
		// INSERT COLOR Red
		Color colorRed = new Color();
		colorRed.setName("Red");
		colorRed.setCode("#FF0000");
		colors.add(colorRed);
		
		// INSERT COLOR White
		Color colorWhite = new Color();
		colorWhite.setName("White");
		colorWhite.setCode("#000000");
		colors.add(colorWhite);
		
		person1.setColors(colors);
		insertHperson.add(person1);
		
		person2.setColors(colors);
		insertHperson.add(person2);
		
		insertHCompany.setHpersons(insertHperson);
		dao.insert(insertHCompany);
		
		Long generatedId = insertHCompany.getId();
		log.debug("insert generatedId:" + generatedId);
		
		
		// Update
		HCompany hcompany = dao.findOne(generatedId);
		log.debug("update generatedId:" + generatedId);
		hcompany.setName("Zcompany");
		
		HPerson hperson = new HPerson();
		hperson.setName("Candy");
		hperson.setIdNo("abc");
		
		Collection<Color> colorsUpdate = new ArrayList<Color>();
		Color colorBlack = new Color();
		colorBlack.setName("Black");
		colorBlack.setCode("#FFFFFF");
		colorsUpdate.add(colorBlack);
		hperson.setColors(colorsUpdate);
		hcompany.getHpersons().add(hperson);
		
		dao.update(hcompany);
		
		// Delete
		dao.delete(generatedId);
		log.debug("delete generatedId:" + generatedId);
		
		
		// check the final size should equals to original size
		companys = dao.findAll();
		int finalSize = companys.size();
		
		assertEquals(originalSize, finalSize);
	}
	
}
