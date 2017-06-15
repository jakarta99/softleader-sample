package tw.com.softleader.sample.games;
import static org.junit.Assert.*;

import org.junit.Test;

public class GameServiceTesting {

	public void GameServiceTest(){
	@Test
 GameService gamer = new GameService();
	
        String games[] = gamer.getall();
	
	assertEquals("Detiny2", games[0]);
	assertEquals("Witcher3",games[1]);
	assertEquals("Fallout4",games[2]);
}
}