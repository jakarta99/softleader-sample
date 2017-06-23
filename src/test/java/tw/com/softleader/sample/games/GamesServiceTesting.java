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

		assertEquals("Destiny2", games.getOne(1).getName());
		assertEquals(1, games.getOne(1).getId());
		assertEquals("Fps", games.getOne(1).getType());
	}

	@Test
	public void testGetAll() {
		GameService games = new GameService();

		assertEquals("Destiny2", games.getOne(1).getName());
		assertEquals(1, games.getOne(1).getId());
		assertEquals("Fps", games.getOne(1).getType());
		assertEquals("Witcher3", games.getOne(2).getName());
		assertEquals(2, games.getOne(2).getId());
		assertEquals("Rpg", games.getOne(2).getType());
		assertEquals("Fallout4", games.getOne(3).getName());
		assertEquals(3, games.getOne(3).getId());
		assertEquals("Rpg", games.getOne(3).getType());
	}

	@Test
	public void testInsert() {
		GameService games = new GameService();

		String b = "WOW";
		String c = "MMORPG";
		Game game4 = new Game();

		game4.setId(4);
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

		String bb = "LOL";
		String cc = "MOBA";
		gaming.setId(5);
		gaming.setName(bb);
		gaming.setType(cc);

		games.update(gaming);

		assertEquals(5, gaming.getId());
		assertEquals(bb, gaming.getName());
		assertEquals(cc, gaming.getType());
	}

	@Test
	public void testDelete() {
		GameService games = new GameService();
		games.delete(1);
		assertEquals("Witcher3", games.getOne(2).getName());
		assertEquals("Rpg", games.getOne(2).getType());
		assertEquals(2, games.getOne(2).getId());
	}

	@Test
	public void testInsertUpdateDelete() {

		String w = "OW";
		String e = "Fps";
		String t = "Horizon Zero Dawn";
		String y = "Rpg";
		Game game5 = new Game();
		GameService games = new GameService();
		game5.setId(6);
		game5.setName(w);
		game5.setType(e);

		games.insert(game5);
		assertEquals(w, games.getOne(6).getName());
		assertEquals(e, games.getOne(6).getType());

		Game patch = new Game();
		patch.setId(6);
		patch.setName(t);
		patch.setType(y);

		games.update(patch);
		patch.setId(game5.getId());
		gameService.update(patch);
		assertEquals(t, games.getOne(6).getName());
		assertEquals(y, games.getOne(6).getType());

		gameService.delete(6);
		assertEquals(t, games.getOne(6).getName());
		assertEquals(y, games.getOne(6).getType());

	}

}
