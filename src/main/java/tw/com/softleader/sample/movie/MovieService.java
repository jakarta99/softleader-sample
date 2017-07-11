package tw.com.softleader.sample.movie;

import java.util.Collection;
import tw.com.softleader.sample.commons.GenericService;

public class MovieService implements GenericService<Movie> {

	MovieDao movieDao = new MovieDao();

	@Override
	public Movie getOne(Long id) {
		return movieDao.findOne(id);
	}

	@Override
	public Collection<Movie> getAll() {
		return movieDao.findAll();
	}

	@Override
	public void insert(Movie entity) {
		movieDao.insert(entity);
	}

	@Override
	public void update(Movie entity) {
		movieDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		movieDao.delete(id);
	}
}