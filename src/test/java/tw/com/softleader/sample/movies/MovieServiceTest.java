package tw.com.softleader.sample.movies;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import org.junit.Test;

public class MovieServiceTest {
	

	@Test
	public void testString() {
		MovieService movieService = new MovieService();
		String[] movies=movieService.getall();
		for (String movie : movies) {
			assertEquals("Captain of America", movies[0]);
			assertEquals("Silence of the lambs", movies[1]);
			assertEquals("King Arthor legend of the sword", movies[2]);
			assertEquals("Crimson Peak", movies[3]);
			assertEquals("The Godfather", movies[4]);
			
		}
	}

}
