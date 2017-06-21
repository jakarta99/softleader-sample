package tw.com.softleader.sample.games;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

@SuppressWarnings("unused")
public class GameServiceTest {

	private GameService gameService = new GameService();//

	@Test
	public void testGetOne() {
		GameService games = new GameService();

		assertEquals("Destiny2", games.getOne(0).getName());
		assertEquals("Witcher3", games.getOne(1).getName());
		assertEquals("Fallout4", games.getOne(2).getName());
	}

	@Test
	public void testGetAll() {

		GameService games = new GameService();

		assertEquals("Destiny2", games.getOne(0).getName());
		assertEquals(1, games.getOne(0).getId());
		assertEquals("Fps", games.getOne(0).getType());
		assertEquals("Witcher3", games.getOne(1).getName());
		assertEquals(2, games.getOne(1).getId());
		assertEquals("Rpg", games.getOne(1).getType());
		assertEquals("Fallout4", games.getOne(2).getName());
		assertEquals(3, games.getOne(2).getId());
		assertEquals("Rpg", games.getOne(2).getType());
	}

	@Test
	public void testInsert() {

		int a = 4;
		String b = "WOW";
		String c = "MMORPG";
		Game game4 = new Game();
		game4.setId(a);
		game4.setName(b);
		game4.setType(c);
		gameService.insert(game4);
		assertEquals(4, game4.getId());
		assertEquals(b, game4.getName());
		assertEquals(c, game4.getType());

	}

	@Test
	public void testUpdate() {
		GameService games = new GameService();
		//

	}

	@Test
	public void testDelete() {
		GameService games = new GameService();
		games.delete(0);
		assertEquals("Witcher3", games.getOne(0).getName());
		assertEquals("Rpg", games.getOne(0).getType());
		assertEquals(2, games.getOne(0).getId());
	}

}
