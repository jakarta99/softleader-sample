package tw.com.softleader.sample.sports;

import java.util.ArrayList;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class SportsService implements GenericService<Sport> {

	private List<Sport> sports = new ArrayList<Sport>();

	public SportsService() {
		Sport sport1 = new Sport();
		sport1.setId(3);
		sport1.setName("Basketball");
		sport1.setPeople("5");

		Sport sport2 = new Sport();
		sport2.setId(6);
		sport2.setName("Baseball");
		sport2.setPeople("9");

		Sport sport3 = new Sport();
		sport3.setId(9);
		sport3.setName("Swimming");
		sport3.setPeople("1");

		sports.add(sport1);
		sports.add(sport2);
		sports.add(sport3);

	}

	@Override
	public Sport getOne(int id) {
		for (int i = 0; i < sports.size(); i++) {
			if (sports.get(i).getId() == id) {
				return sports.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Sport> getAll() {
		return sports;
	}

	@Override
	public void insert(Sport data) {
		sports.add(data);

	}

	@Override
	public void update(Sport data) {
		for (int i = 0; i < sports.size(); i++) {
			if (sports.get(i).getId() == data.getId()) {
				sports.set(i, data);
			}
		}

	}

	@Override
	public void delete(int id) {
		for (int i = 0; i < sports.size(); i++) {
			if (sports.get(i).getId() == id) {
				sports.remove(i);
			}
		}

	}

}