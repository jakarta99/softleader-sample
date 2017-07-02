package tw.com.softleader.sample.game;

//import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

public class GameServiceTest {

	private GameService gameService = new GameService();

	@Test
	public void testGetOne() {
		System.out.println(gameService.getOne((long) 2));
	}

	@Test
	public void testGetAll() {

		Collection<Game> games = gameService.getAll();

		for (Game game : games) {
			System.out.println(game);
		}
	}

	@Test
	public void testInsertUpdateDelete() {
		Game Input = new Game();
		Input.setId((long) 20);
		Input.setName("Fallout4");
		Input.setType("Fps");
		gameService.insert(Input);
        
		Game patch = new Game();
		
		
		patch.setId((long) 20);
		patch.setName("WOW");
		patch.setType("MMORPG");
		gameService.update(patch);
		
		gameService.delete((long) 20);

	}

}