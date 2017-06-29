package tw.com.softleader.sample.movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class MovieService implements GenericService<Movie> {

	private List<Movie> movies = new ArrayList<Movie>();

	@Override
	public Movie getOne(Long id) {
		return null;
	}

	@Override
	public Collection<Movie> getAll() {
		return null;
	}

	@Override
	public void insert(Movie entity) {
	}

	@Override
	public void update(Movie entity) {
	}

	@Override
	public void delete(Long id) {
	}
}