package tw.com.softleader.sample.human;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanDao humanDao;
	
	@Override
	public Human getOne(Long id) {
		return getDao().findOne(id);
	}

	@Override
	public Collection<Human> getAll() {
		return getDao().findAll();
	}

	@Override
	public void insert(Human entity) {
		getDao().insert(entity);
	}

	@Override
	public void update(Human entity) {
		getDao().update(entity);
	}

	@Override
	public void delete(Long id) {
		getDao().delete(id);
	}

	private HumanDao getDao() {
		if (humanDao == null) {
			humanDao = new HumanDao();
			return humanDao;
		} else {
			return humanDao;
		}
	}
	
}
