package tw.com.softleader.sample.games;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

@SuppressWarnings("unused")
public class GameServiceTest {

	private GameService gameService = new GameService();//
	List<Game> game = gameService.getAll();

	@Test
	public void testGetOne() {
		GameService games = new GameService();

		assertEquals("Destiny2", games.getOne(0).getName());
		assertEquals("Witcher3", games.getOne(1).getName());
		assertEquals("Fallout4", games.getOne(2).getName());
	}

	@Test
	public void testGetAll() {

		// GameService games = new GameService();
		//gameService.getAll();
		assertEquals("Destiny2", game.get(0).getName());
		assertEquals(1, game.get(0).getId());
		assertEquals("Fps", game.get(0).getType());
		assertEquals("Witcher3", game.get(1).getName());
		assertEquals(2, game.get(1).getId());
		assertEquals("Rpg", game.get(1).getType());
		assertEquals("Fallout4", game.get(2).getName());
		assertEquals(3, game.get(2).getId());
		assertEquals("Rpg", game.get(2).getType());
	}

	@Test
	public void testInsert() {
		GameService games = new GameService();
		int a = 4;
		String b = "WOW";
		String c = "MMORPG";
		Game game4 = new Game();

		game4.setId(a);
		game4.setName(b);
		game4.setType(c);
		games.insert(game4);
		assertEquals(4, game4.getId());
		assertEquals(b, game4.getName());
		assertEquals(c, game4.getType());

	}

	@Test
	public void testUpdate() {
		GameService games = new GameService();
		Game gaming = new Game();
		int aa = 5;
		String bb = "LOL";
		String cc = "MOBA";
		gaming.setId(aa);
		gaming.setName(bb);
		gaming.setType(cc);

		games.update(gaming);

		assertEquals(aa, gaming.getId());
		assertEquals(bb, gaming.getName());
		assertEquals(cc, gaming.getType());
	}

	@Test
	public void testDelete() {
		 GameService games = new GameService();
		games.delete(1);
		assertEquals("Witcher3", game.get(1).getName());
		assertEquals("Rpg", game.get(1).getType());
		assertEquals(1, game.get(0).getId());
	}

}
