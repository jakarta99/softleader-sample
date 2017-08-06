package tw.com.softleader.sample.movie;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class MCompanyService implements GenericService<MCompany>{
	
	MCompanyDao mCompanyDao = new MCompanyDao();
	
	@Override
	public MCompany getOne(Long id) {
		mCompanyDao.findOne(id);
		return null;
	}

	@Override
	public Collection<MCompany> getAll() {
		mCompanyDao.findAll();
		return null;
	}

	@Override
	public void insert(MCompany entity) {
		mCompanyDao.insert(entity);;
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

