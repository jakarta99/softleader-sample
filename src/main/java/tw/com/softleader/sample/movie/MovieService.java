package tw.com.softleader.sample.movie;

import java.util.ArrayList;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class MovieService implements GenericService<Movie> {

	private List<Movie> movies = new ArrayList<Movie>();

	public MovieService() {
		Movie movies1 = new Movie();
		movies1.setId(1);
		movies1.setName("Captain of America");
		movies1.setPrice("100");

		Movie movies2 = new Movie();
		movies2.setId(2);
		movies2.setName("Silence of the lambs");
		movies2.setPrice("200");

		Movie movies3 = new Movie();
		movies3.setId(3);
		movies3.setName("King Arthor legend of the sword");
		movies3.setPrice("300");

		Movie movies4 = new Movie();
		movies4.setId(4);
		movies4.setName("Crimson Peak");
		movies4.setPrice("400");

		Movie movies5 = new Movie();
		movies5.setId(5);
		movies5.setName("The Godfather");
		movies5.setPrice("500");

		movies.add(movies1);
		movies.add(movies2);
		movies.add(movies3);
		movies.add(movies4);
		movies.add(movies5);
	}

	@Override
	public Movie getOne(int id) {
		for (int i = 0; i < movies.size(); i++) {
			if (id == movies.get(i).getId()) {
				return movies.get(i);
			}
		}
		return null;

	}

	@Override
	public List<Movie> getAll() {
		return movies;
	}

	@Override
	public void insert(Movie data) {
		movies.add(data);
	}

	@Override
	public void update(Movie data) {
		for (int i = 0; i < movies.size(); i++) {
			if (data.getId() == movies.get(i).getId()) {
				movies.set(i, data);
			}
		}

	}

	@Override
	public void delete(int id) {
		for (int i = 0; i < movies.size(); i++) {
			if (id == movies.get(i).getId()) {
				movies.remove(i);
			}
		}
	}
}
