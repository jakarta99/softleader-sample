package tw.com.softleader.sample.sports;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SportsServiceTest {
	SportsService test1 = new SportsService();
	List<Sport> test1List = test1.getAll();

	@Test
	public void getAllTest() {
		assertEquals(test1List.get(0).getName(), "Basketball");
		assertEquals(test1List.get(1).getName(), "Baseball");
		assertEquals(test1List.get(2).getName(), "Swimming");
	}
	

	@Test
	public void getOneTest(){
		assertEquals(test1.getOne(0).getName(), "Basketball");

	}
	
	@Test
	public void testInsertAndUpdateAndDelete(){
		Sport Tennis = new Sport();
		Tennis.setId(test1List.size()+1);
		Tennis.setName("Tennis");
		Tennis.setPeople("2 or 4");
		test1.insert(Tennis);
		assertEquals(test1.getOne(3).getName(), "Tennis");
		
		Sport Boxing = new Sport();
		Boxing.setId(3);
		Boxing.setName("Boxing");
		Boxing.setPeople("2");
		test1.update(Boxing);
		assertEquals(test1.getOne(3).getName(), "Boxing");
		
		test1.delete(3);
		assertEquals(test1.getAll().contains(Boxing),false);
	}	

}