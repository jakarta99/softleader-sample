package tw.com.softleader.sample.sports;

import java.util.ArrayList;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class SportsService implements GenericService<Sport> {

	private List<Sport> sports = new ArrayList<Sport>();

	public SportsService() {
		Sport sport1 = new Sport();
		sport1.setId(1);
		sport1.setName("Basketball");
		sport1.setPeople("5");

		Sport sport2 = new Sport();
		sport2.setId(1);
		sport2.setName("Baseball");
		sport2.setPeople("9");

		Sport sport3 = new Sport();
		sport3.setId(1);
		sport3.setName("Swimming");
		sport3.setPeople("1");

		sports.add(sport1);
		sports.add(sport2);
		sports.add(sport3);

	}

	@Override
	public Sport getOne(int id) {
		return sports.get(id);
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
		sports.set(data.getId(), data);

	}

	@Override
	public void delete(int id) {
		sports.remove(id);

	}

}