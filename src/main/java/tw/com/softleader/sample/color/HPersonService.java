package tw.com.softleader.sample.color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class HPersonService implements GenericService<HPerson>{
	
	private List<HPerson> hPersons = new ArrayList<HPerson>();
	HPersonDao dao = new HPersonDao();
	

	@Override
	public HPerson getOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Collection<HPerson> getAll() {
		return dao.findAll();
	}

	@Override
	public void insert(HPerson entity) {
		dao.insert(entity);
	}

	@Override
	public void update(HPerson entity) {
		dao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}

}
