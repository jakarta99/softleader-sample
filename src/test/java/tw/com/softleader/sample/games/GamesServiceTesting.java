package tw.com.softleader.sample.games;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameServiceTesting {
	@Test
	public void GameServiceTest() {

		GameService gamer = new GameService();

		String games[] = gamer.getAll();

		assertEquals("Detiny2", games[0]);
		assertEquals("Witcher3", games[1]);
		assertEquals("Fallout4", games[2]);
	}
}
//