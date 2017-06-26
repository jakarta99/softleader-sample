package tw.com.softleader.sample.games;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

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
		assertEquals(3, game.size());

	}

}
//////