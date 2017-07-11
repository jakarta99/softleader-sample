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
public class KPersonService implements GenericService<KPerson> {

	@Override
	public KPerson getOne(Long id) {
		KPersonDao kPersonDao = new KPersonDao();
		return kPersonDao.findOne(id);
	}

	@Override
	public Collection<KPerson> getAll() {

		KPersonDao kPersonDao = new KPersonDao();
		
		return kPersonDao.findAll();
	}

	@Override
	public void insert(KPerson entity) {
		KPersonDao kPersonDao = new KPersonDao();
		kPersonDao.insert(entity);
	}

	@Override
	public void update(KPerson entity) {
		KPersonDao kPersonDao = new KPersonDao();
		kPersonDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		KPersonDao kPersonDao = new KPersonDao();
		kPersonDao.delete(id);
		
	}
	
	
}
