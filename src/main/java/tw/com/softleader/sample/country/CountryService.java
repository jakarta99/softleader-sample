package tw.com.softleader.sample.country;

import java.util.*;
import tw.com.softleader.sample.commons.GenericService;

public class CountryService implements GenericService<Country> {

	private List<Country> countries = new ArrayList<Country>();

	public CountryService() {

		Country country1 = new Country();
		country1.setId(1);
		country1.setName("Taiwan");
		country1.setSize("Small");

		Country country2 = new Country();
		country2.setId(2);
		country2.setName("Japan");
		country2.setSize("Medium");

		Country country3 = new Country();
		country3.setId(3);
		country3.setName("Malaysia");
		country3.setSize("Big");

		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
	}

	@Override
	public Country getOne(int id) {
		for (int i = 0; i < countries.size(); i++) {
			if (countries.get(i).getId() == id) {
				return countries.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Country> getAll() {
		return countries;
	}

	@Override
	public void insert(Country data) {
		countries.add(data);
	}

	@Override
	public void update(Country data) {
		for (int i = 0; i < countries.size(); i++) {
			if (countries.get(i).getId() == data.getId()) {
				countries.get(i).setName(data.getName());
				countries.get(i).setSize(data.getSize());
				countries.get(i).setId(data.getId());
			}
		}
	}

	@Override
	public void delete(int id) {
		for (int i = 0; i < countries.size(); i++) {
			if (countries.get(i).getId() == id) {
				countries.remove(i);
			}
		}
	}
}
