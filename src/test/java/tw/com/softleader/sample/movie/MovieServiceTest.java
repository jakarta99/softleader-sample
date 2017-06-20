package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class MovieServiceTest {
	
	private MovieService movieService = new MovieService();

	@Test
	public void testGetMovie() {
		List<Movie> movies = movieService.getAll();
		
		assertEquals(movies.get(0).getName(),"Captain of America");
		assertEquals(movies.get(0).getPrice(),"100");
		assertEquals(movies.get(1).getName(),"Silence of the lambs");
		assertEquals(movies.get(1).getPrice(),"200");
		assertEquals(movies.get(2).getName(),"King Arthor legend of the sword");
		assertEquals(movies.get(2).getPrice(),"300");
		assertEquals(movies.get(3).getName(),"Crimson Peak");
		assertEquals(movies.get(3).getPrice(),"400");
		assertEquals(movies.get(4).getName(),"The Godfather");
		assertEquals(movies.get(4).getPrice(),"500");
	}
}
