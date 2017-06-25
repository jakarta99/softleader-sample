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
	public void getOneTest() {
		assertEquals(test1.getOne(3).getId(),3);
		assertEquals(test1.getOne(3).getName(), "Basketball");
		assertEquals(test1.getOne(12),null);

	}

	@Test
	public void testInsertAndUpdateAndDelete() {
		Sport Tennis = new Sport();
		Tennis.setId(12);
		Tennis.setName("Tennis");
		Tennis.setPeople("2 or 4");
		
		test1.insert(Tennis);
		assertEquals(test1.getOne(12).getName(), "Tennis");

		Sport Boxing = new Sport();
		Boxing.setId(12);
		Boxing.setName("Boxing");
		Boxing.setPeople("2");
		
		test1.update(Boxing);
		assertEquals(test1.getOne(12).getName(), "Boxing");

		test1.delete(12);
		assertEquals(test1.getAll().contains(Boxing), false);
		assertEquals(test1.getAll().contains(test1List.get(1)), true);
	}

}