package tw.com.softleader.sample.books;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class BCompanyService implements GenericService<BCompany>{

	BCompanyDao bcompanyDao = new BCompanyDao();
	@Override
	public BCompany getOne(Long id) {
		return bcompanyDao.findOne(id);
	}

	@Override
	public Collection<BCompany> getAll() {
		return bcompanyDao.findAll();
	}

	@Override
	public void insert(BCompany entity) {
		bcompanyDao.insert(entity);
	}

	@Override
	public void update(BCompany entity) {
		bcompanyDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		bcompanyDao.delete(id);
	}

}
