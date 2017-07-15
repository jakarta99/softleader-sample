package tw.com.softleader.sample.movie;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class MCompanyService implements GenericService<MCompany> {

	MCompanyDao mCompanyDao = new MCompanyDao();
	
	@Override
	public MCompany getOne(Long id) {
		return mCompanyDao.findOne(id);
	}

	@Override
	public Collection<MCompany> getAll() {
		return mCompanyDao.findAll();
	}

	@Override
	public void insert(MCompany entity) {	
		mCompanyDao.insert(entity);
	}

	@Override
	public void update(MCompany entity) {
		mCompanyDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		mCompanyDao.delete(id);
	}
}

