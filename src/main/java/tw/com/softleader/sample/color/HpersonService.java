package tw.com.softleader.sample.color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class HpersonService implements GenericService<Hperson>{
	
	private List<Hperson> hPersons = new ArrayList<Hperson>();
	HpersonDao dao = new HpersonDao();
	

	@Override
	public Hperson getOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Collection<Hperson> getAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Hperson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Hperson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	

}
