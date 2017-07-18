package tw.com.softleader.sample.human;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class XPersonDaoTest {

	private XPersonDao xPersonDao = new XPersonDao();

	@Test
	public void test() {
		
		Collection<XPerson> xPersons = xPersonDao.findAll();
		int originalSize = xPersons.size();
		
		
		XPerson samuel = new XPerson();
		samuel.setIdNo("ooooooooo1");
		samuel.setName("Samuel");
		
		Collection<Human> humans = new ArrayList<Human>();
		Human human1 = new Human();
		human1.setName("ONE");
		human1.setGender("BOY");
		humans.add(human1);
		
		Human human2 = new Human();
		human2.setName("TWO");
		human2.setGender("GIRL");
		humans.add(human2);
		
		Human human3 = new Human();
		human3.setName("THREE");
		human3.setGender("GIRL");
		humans.add(human3);
		
		samuel.setHumans(humans);
		
		xPersonDao.insert(samuel);
		
		Long samuelPersonId = samuel.getId();
		
		
		// Try to modify the data
		XPerson entity = xPersonDao.findOne(samuelPersonId);
		
		Human human4 = new Human();
		human4.setName("Juice");
		human4.setGender("GIRL");
		entity.getHumans().add(human4);
		
		xPersonDao.update(entity);
		
		xPersonDao.delete(samuelPersonId);
		assertNull(xPersonDao.findOne(samuelPersonId));
		
		
		// check the final size should equals to original size
		xPersons = xPersonDao.findAll();
		int finalSize = xPersons.size();
		
		
		assertEquals(originalSize, finalSize);
			
	}

}
