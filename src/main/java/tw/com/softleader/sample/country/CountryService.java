package tw.com.softleader.sample.country;

import java.util.*;
import tw.com.softleader.sample.commons.GenericService;

public class CountryService implements GenericService<Country> {
	CountryDao countryDao = new CountryDao();

	@Override
	public Country getOne(Long id) {
		return countryDao.findOne(id);
	}

	@Override
	public Collection<Country> getAll() {
		return countryDao.findAll();
	}

	@Override
	public void insert(Country entity) {
		countryDao.insert(entity);
	}

	@Override
	public void update(Country entity) {
		countryDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		countryDao.delete(id);
	}

}
