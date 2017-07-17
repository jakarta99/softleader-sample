package tw.com.softleader.sample.KPerson;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * KPerson的服務
 * 
 * @author Robert
 *
 */
public class KCompanyService implements GenericService<KCompany> {

	@Override
	public KCompany getOne(Long id) {
		KCompanyDao kCompanyDao = new KCompanyDao();
		return kCompanyDao.findOne(id);
	}

	@Override
	public Collection<KCompany> getAll() {

		KCompanyDao kCompanyDao = new KCompanyDao();
		
		return kCompanyDao.findAll();
	}

	@Override
	public void insert(KCompany entity) {
		KCompanyDao kCompanyDao = new KCompanyDao();
		kCompanyDao.insert(entity);
	}

	@Override
	public void update(KCompany entity) {
		KCompanyDao kCompanyDao = new KCompanyDao();
		kCompanyDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		KCompanyDao kCompanyDao = new KCompanyDao();
		kCompanyDao.delete(id);
		
	}
	
	
}
