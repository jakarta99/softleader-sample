package tw.com.softleader.sample.sports;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class SPersonService implements GenericService<SPerson> {
	
	SPersonDao SPersonD = new SPersonDao();


	@Override
	public SPerson getOne(Long id) {
		// TODO Auto-generated method stub
		return SPersonD.findOne(id);
	}

	@Override
	public Collection<SPerson> getAll() {
		// TODO Auto-generated method stub
		return SPersonD.findAll();
	}

	@Override
	public void insert(SPerson entity) {
		// TODO Auto-generated method stub
		SPersonD.insert(entity);
		
	}

	@Override
	public void update(SPerson entity) {
		// TODO Auto-generated method stub
		SPersonD.update(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SPersonD.delete(id);
	}
	

}
