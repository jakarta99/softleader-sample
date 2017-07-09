package tw.com.softleader.sample.books;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class PersonService implements GenericService<Person>{
	
	PersonDao personDao = new PersonDao();

	@Override
	public Person getOne(Long id) {
		return personDao.findOne(id);
	}

	@Override
	public Collection<Person> getAll() {
		return personDao.findAll();
	}

	@Override
	public void insert(Person entity) {
		personDao.insert(entity);
		
	}

	@Override
	public void update(Person entity) {
		personDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		personDao.delete(id);
		
	}


}
