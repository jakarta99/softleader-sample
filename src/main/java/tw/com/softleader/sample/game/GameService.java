package tw.com.softleader.sample.game;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class GameService implements GenericService<Game> {

	@Override
	public Game getOne(Long id) {
		GameDao gameDao = new GameDao();
		return gameDao.findOne(id);
	}

	@Override
	public Collection<Game> getAll() {
		GameDao gameDao = new GameDao();
		return gameDao.findAll();
	}

	@Override
	public void insert(Game entity) {
		GameDao gameDao = new GameDao();
		gameDao.insert(entity);

	}

	@Override
	public void update(Game entity) {
		GameDao gameDao = new GameDao();
		gameDao.insert(entity);
	}

	@Override
	public void delete(Long id) {
		GameDao gameDao = new GameDao();
		gameDao.delete(id);
	}

}