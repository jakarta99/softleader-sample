package tw.com.softleader.sample.books;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class BPersonService implements GenericService<BPerson>{
	
	BPersonDao personDao = new BPersonDao();

	@Override
	public BPerson getOne(Long id) {
		return personDao.findOne(id);
	}

	@Override
	public Collection<BPerson> getAll() {
		return personDao.findAll();
	}

	@Override
	public void insert(BPerson entity) {
		personDao.insert(entity);
		
	}

	@Override
	public void update(BPerson entity) {
		personDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		personDao.delete(id);
		
	}


}
