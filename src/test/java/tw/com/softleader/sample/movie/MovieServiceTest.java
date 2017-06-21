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
		Movie movie = movieService.getOne(1);
		assertEquals(movie.getId(),2);
		assertEquals(movie.getName(),"Silence of the lambs");
		assertEquals(movie.getPrice(),"200");
	}

	@Test
	public void testInsert() {
		Movie movie = new Movie();
		movie.setId(6);
		movie.setName("Transformers:The Last Knight");
		movie.setPrice("600");

		movieService.insert(movie);
		assertEquals(movie.getId(),6);
		assertEquals(movie.getName(), "Transformers:The Last Knight");
		assertEquals(movie.getPrice(), "600");
	}
	
	@Test
	public void testUpdate() {
		Movie movie = new Movie();
		movie.setId(5);
		movie.setName("The Godfather2");
		movie.setPrice("550");
		
		movieService.update(movie);
		assertEquals(movie.getId(),5);
		assertEquals(movie.getName(),"The Godfather2");
		assertEquals(movie.getPrice(),"550");
	}

	@Test
	public void testDelete() {
		Movie movie = movies.get(1);
		movieService.delete(1);
		assertEquals(movies.contains(movie),false);
	}

}
