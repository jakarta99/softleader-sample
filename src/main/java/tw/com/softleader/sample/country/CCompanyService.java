package tw.com.softleader.sample.country;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;


public class CCompanyService implements GenericService<CCompany> {
	CCompanyDao cCompanyDao = new CCompanyDao();

	@Override
	public CCompany getOne(Long id) {
		return cCompanyDao.findOne(id);
	}

	@Override
	public Collection<CCompany> getAll() {
		return cCompanyDao.findAll();
	}

	@Override
	public void insert(CCompany entity) {
	cCompanyDao.insert(entity);
	}

	@Override
	public void update(CCompany entity) {
		cCompanyDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		cCompanyDao.delete(id);
	}

}
