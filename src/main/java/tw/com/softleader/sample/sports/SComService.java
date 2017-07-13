package tw.com.softleader.sample.sports;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class SComService implements GenericService<SCom> {

	SComDao SComD = new SComDao();
	
	@Override
	public SCom getOne(Long id) {
		// TODO Auto-generated method stub
		return SComD.findOne(id);
	}

	@Override
	public Collection<SCom> getAll() {
		// TODO Auto-generated method stub
		return SComD.findAll();
	}

	@Override
	public void insert(SCom entity) {
		// TODO Auto-generated method stub
		SComD.insert(entity);
	}

	@Override
	public void update(SCom entity) {
		// TODO Auto-generated method stub
		SComD.update(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SComD.delete(id);
	}

	
	

}
