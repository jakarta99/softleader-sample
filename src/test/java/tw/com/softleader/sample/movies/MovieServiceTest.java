package tw.com.softleader.sample.movies;

import org.junit.Test;

public class MovieServiceTest {
	
	private void assertEquals(String string, String string2, String string3, String string4, String string5,
			String movie) {
	}
	@Test
	public void testString() {
		MovieService movieService = new MovieService();
		for (String movie : movieService.movies) {
			assertEquals("Captain of America", "Silence of the lambs", "King Arthor legend of the sword",
					"Crimson Peak", "The Godfather", movie);
		}
	}

}
