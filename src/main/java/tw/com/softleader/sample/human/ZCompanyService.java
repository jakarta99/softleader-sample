package tw.com.softleader.sample.human;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class ZCompanyService implements GenericService<ZCompany> {

	private ZCompanyDao zCompanyDao;
	
	@Override
	public ZCompany getOne(Long id) {
		// TODO Auto-generated method stub
		return getDao().findOne(id);
	}

	@Override
	public Collection<ZCompany> getAll() {
		// TODO Auto-generated method stub
		return getDao().findAll();
	}

	@Override
	public void insert(ZCompany entity) {
		// TODO Auto-generated method stub
		getDao().insert(entity);
	}

	@Override
	public void update(ZCompany entity) {
		// TODO Auto-generated method stub
		getDao().update(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		getDao().delete(id);
	}
	
	private ZCompanyDao getDao() {
		if (zCompanyDao == null) {
			zCompanyDao = new ZCompanyDao();
			return zCompanyDao;
		}
		return zCompanyDao;
	}

}
