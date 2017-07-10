package tw.com.softleader.sample.notebook;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class WPersonService implements GenericService<WPerson> {

	@Override
	public WPerson getOne(Long id) {
		return new WPersonDao().findOne(id);
	}

	@Override
	public Collection<WPerson> getAll() {
		return new WPersonDao().findAll();
	}

	@Override
	public void insert(WPerson entity) {
		new WPersonDao().insert(entity);
	}

	@Override
	public void update(WPerson entity) {
		new WPersonDao().update(entity);
		
	}

	@Override
	public void delete(Long id) {
		new WPersonDao().delete(id);
		
	}

}
