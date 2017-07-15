package tw.com.softleader.sample.game;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class GcompanyService implements GenericService<Gcompany> {
	GcompanyDao companyDao = new GcompanyDao();

	@Override
	public Gcompany getOne(Long id) {

		return companyDao.findOne(id);
	}

	@Override
	public Collection<Gcompany> getAll() {

		return companyDao.findAll();
	}

	@Override
	public void insert(Gcompany entity) {
		companyDao.insert(entity);
	}

	@Override
	public void update(Gcompany entity) {
		companyDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		companyDao.delete(id);
	}
}