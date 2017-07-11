package tw.com.softleader.sample.game;


import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;
//import tw.com.softleader.sample.game.GPersonGame;
public class GPersonService implements GenericService<GPerson> {

	@Override
	public GPerson getOne(Long pId) {
		GPersonDao personDao = new GPersonDao();
		return personDao.findOne(pId);
	}

	@Override
	public Collection<GPerson> getAll() {
		GPersonDao personDao = new GPersonDao();
		return personDao.findAll();
	}

	@Override
	public void insert(GPerson entity) {
		GPersonDao personDao = new GPersonDao();
		personDao.insert(entity);

	}

	@Override
	public void update(GPerson entity) {
		GPersonDao personDao = new GPersonDao();
		personDao.insert(entity);
	}

	@Override
	public void delete(Long pId) {
		GPersonDao personDao = new GPersonDao();
		personDao.delete(pId);
	}
	
	
	public  List<Game>getGameByPerson(Long pId){
		GPersonDao personDao = new GPersonDao();
	return personDao.getGameByPerson(pId);
	   
   }
	

}