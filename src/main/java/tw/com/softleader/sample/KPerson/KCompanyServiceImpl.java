package tw.com.softleader.sample.KPerson;

import java.util.Collection;

/**
 * 
 * KPerson的服務
 * 
 * @author Robert
 *
 */
public class KCompanyServiceImpl implements KCompanyService {
	public String defaultName = "noName";  
	public KCompanyDao kCompanyDao;
	public void setkCompanyDao(KCompanyDao kCompanyDao) {
		this.kCompanyDao = kCompanyDao;
	}

	public KCompanyServiceImpl(){
		
	}

	public KCompanyServiceImpl(String name) {
		this.defaultName = name;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	@Override
	public KCompany getOne(Long id) {
		return kCompanyDao.findOne(id);
	}

	@Override
	public Collection<KCompany> getAll() {
		System.out.println(defaultName);
		
		return kCompanyDao.findAll();
	}

	@Override
	public void insert(KCompany entity) {
		kCompanyDao.insert(entity);
	}

	@Override
	public void update(KCompany entity) {
		kCompanyDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		kCompanyDao.delete(id);
		
	}

}
