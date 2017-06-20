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
		assertEquals(movies.getId(),2);
		assertEquals(movies.getName(),"Silence of the lambs");
		assertEquals(movies.getPrice(),"200");
	}

	@Test
	public void testInsert() {
		Movie movies = new Movie();
		movies.setId(6);
		movies.setName("Transformers:The Last Knight");
		movies.setPrice("600");

		movieService.insert(movies);
		assertEquals(movies.getId(),6);
		assertEquals(movies.getName(), "Transformers:The Last Knight");
		assertEquals(movies.getPrice(), "600");
	}
	
	@Test
	public void testUpdate() {
		Movie movies = new Movie();
		movies.setId(5);
		movies.setName("The Godfather2");
		movies.setPrice("550");
		
		movieService.update(movies);
		assertEquals(movies.getId(),5);
		assertEquals(movies.getName(),"The Godfather2");
		assertEquals(movies.getPrice(),"550");
	}

	@Test
	public void testDelete() {
		movieService.delete(3);
		assertEquals(movieService.getAll().contains("Silence of the lambs"), false);
	}

}
