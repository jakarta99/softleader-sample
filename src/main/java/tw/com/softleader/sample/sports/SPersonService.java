package tw.com.softleader.sample.sports;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class SPersonService implements GenericService<SPerson> {
	
	SPersonDao SPersonD = new SPersonDao();


	@Override
	public SPerson getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<SPerson> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(SPerson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SPerson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	

}
