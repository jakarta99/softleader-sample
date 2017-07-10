package tw.com.softleader.sample.movie;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

public class MPersonService implements GenericService<MPerson> {

	MPersonDao mpersonDao = new MPersonDao();

	@Override
	public MPerson getOne(Long id) {
		
		return mpersonDao.findOne(id);
	}

	@Override
	public Collection<MPerson> getAll() {

		return mpersonDao.findAll();
	}

	@Override
	public void insert(MPerson entity) {
		mpersonDao.insert(entity);
	}

	@Override
	public void update(MPerson entity) {
		mpersonDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		mpersonDao.delete(id);
	}

}