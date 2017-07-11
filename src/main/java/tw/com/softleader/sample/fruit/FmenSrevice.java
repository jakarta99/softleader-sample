package tw.com.softleader.sample.fruit;

import java.util.Collection;
import tw.com.softleader.sample.commons.GenericService;

public class FmenSrevice implements GenericService<Fman> {

	FmanDao dao = new FmanDao();

	@Override
	public Fman getOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Collection<Fman> getAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Fman entity) {
		dao.insert(entity);
		
	}

	@Override
	public void update(Fman entity) {
		dao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}

}
