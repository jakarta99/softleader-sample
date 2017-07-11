package tw.com.softleader.sample.country;

import java.util.*;
import tw.com.softleader.sample.commons.GenericService;

public class CPersonService implements GenericService<CPerson> {
	CPersonDao cPersonDao = new CPersonDao();

	@Override
	public CPerson getOne(Long id) {
		return cPersonDao.findOne(id);
	}

	@Override
	public Collection<CPerson> getAll() {
		return cPersonDao.findAll();
	}

	@Override
	public void insert(CPerson entity) {
	cPersonDao.insert(entity);
	}

	@Override
	public void update(CPerson entity) {
		cPersonDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		cPersonDao.delete(id);
	}

}
