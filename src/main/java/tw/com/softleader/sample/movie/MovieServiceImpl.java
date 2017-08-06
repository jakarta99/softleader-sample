package tw.com.softleader.sample.movie;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

	private String defaultPrice = "0";

	public MovieServiceImpl() {
		
	}

	public MovieServiceImpl(String price) {
		this.defaultPrice = price;
	}

	@Override
	public Movie getOne(Long id) {
		MovieDao movieDao = new MovieDao();
		return movieDao.findOne(id);
	}

	@Override
	public Collection<Movie> getAll() {
		MovieDao movieDao = new MovieDao();
		return movieDao.findAll();
	}

	@Override
	public void insert(Movie entity) {
		MovieDao movieDao = new MovieDao();
		movieDao.insert(entity);
	}

	@Override
	public void update(Movie entity) {
		MovieDao movieDao = new MovieDao();
		movieDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		MovieDao movieDao = new MovieDao();
		movieDao.delete(id);
	}
}