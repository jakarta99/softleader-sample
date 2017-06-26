package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class MovieServiceTest {

	private MovieService movieService = new MovieService();
	List<Movie> movies = movieService.getAll();

	@Test
	public void testGetAll() {

		assertEquals(movies.get(0).getId(), 1);
		assertEquals(movies.get(0).getName(), "Captain of America");
		assertEquals(movies.get(0).getPrice(), "100");
		assertEquals(movies.get(1).getId(), 2);
		assertEquals(movies.get(1).getName(), "Silence of the lambs");
		assertEquals(movies.get(1).getPrice(), "200");
		assertEquals(movies.get(2).getId(), 3);
		assertEquals(movies.get(2).getName(), "King Arthor legend of the sword");
		assertEquals(movies.get(2).getPrice(), "300");
		assertEquals(movies.get(3).getId(), 4);
		assertEquals(movies.get(3).getName(), "Crimson Peak");
		assertEquals(movies.get(3).getPrice(), "400");
		assertEquals(movies.get(4).getId(), 5);
		assertEquals(movies.get(4).getName(), "The Godfather");
		assertEquals(movies.get(4).getPrice(), "500");
	}

	@Test
	public void testGetOne() {
		Movie movies = movieService.getOne(1);
		assertEquals(movies.getId(), 2);
		assertEquals(movies.getName(), "Silence of the lambs");
		assertEquals(movies.getPrice(), "200");
	}

	@Test
	public void testInsertUpdateDelete() {
		Movie movie1 = new Movie();
		movie1.setId(6);
		movie1.setName("Transformers:The Last Knight");
		movie1.setPrice("600");
		movieService.insert(movie1);
		assertEquals(movies.get(5).getId(), 6);
		assertEquals(movies.get(5).getName(), "Transformers:The Last Knight");
		assertEquals(movies.get(5).getPrice(), "600");

		Movie movie2 = new Movie();
		movie2.setId(5);
		movie2.setName("The Godfather2");
		movie2.setPrice("550");
		movieService.update(movie2);
		assertEquals(movies.get(4).getId(), 5);
		assertEquals(movies.get(4).getName(), "The Godfather2");
		assertEquals(movies.get(4).getPrice(), "550");

		Movie movie3 = movieService.getOne(1);
		movieService.delete(1);
		assertEquals(movies.contains(movie3), false);
	}
}
