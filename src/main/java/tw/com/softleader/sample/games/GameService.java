package tw.com.softleader.sample.games;

import java.util.ArrayList;
import java.util.List;

public class GameService implements GenericService<Game> {

	private List<Game> games = new ArrayList<Game>();

	public GameService() {

		Game game1 = new Game();
		game1.setId(1);
		game1.setName("Destiny2");
		game1.setType("Fps");

		Game game2 = new Game();
		game2.setId(2);
		game2.setName("Witcher3");
		game2.setType("Rpg");

		Game game3 = new Game();
		game3.setId(3);
		game3.setName("Fallout4");
		game3.setType("Rpg");

		games.add(game1);
		games.add(game2);
		games.add(game3);
	}

	@Override
	public Game getOne(int id) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getId() == id) {
				return games.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Game> getAll() {
		return games;
	}

	@Override
	public void insert(Game data) {
		games.add(data);

	}

	@Override
	public void update(Game data) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getId() == data.getId()) {
				games.get(i).setName(data.getName());
				games.get(i).setType(data.getType());
				games.get(i).setId(data.getId());
			}
		}

	}

	@Override
	public void delete(int id) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getId() == id) {
				games.remove(i);
			}
		}
	}

}