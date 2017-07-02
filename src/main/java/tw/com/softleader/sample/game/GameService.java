package tw.com.softleader.sample.game;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class GameService implements GenericService<Game> {
	GameDao gameD = new GameDao();

	@Override
	public Game getOne(Long id) {

		return gameD.findOne(id);
	}

	@Override
	public Collection<Game> getAll() {

		return gameD.findAll();
	}

	@Override
	public void insert(Game entity) {

		gameD.insert(entity);

	}

	@Override
	public void update(Game entity) {

		gameD.insert(entity);
	}

	@Override
	public void delete(Long id) {

		gameD.delete(id);
	}

}