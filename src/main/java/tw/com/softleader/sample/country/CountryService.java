package tw.com.softleader.sample.country;

import java.util.Arrays;

import tw.com.softleader.sample.commons.GenericService;

public class CountryService implements GenericService {
	private String[] country = { "Taiwan", "Thailand", "Japan" };

	@Override
	public String[] getAll() {

		return country;
	}

	@Override
	public String getOne(int id) {
		return country[id];
	}

	@Override
	public void insert(String data) {
		country = Arrays.copyOf(country, country.length + 1);
		country[country.length - 1] = data;
	}

	@Override
	public void update(String data, int id) {
		country[id] = data;
	}

	@Override
	public void delete(String data) {
		String[] newCountry = new String[country.length - 1];
		int newCountryIndex = 0;
		for (int i = 0; i < country.length; i++) {
			if (!country[i].equals(data)) {
				newCountry[newCountryIndex] = country[i];
				newCountryIndex++;
			}
		}

	}
}
