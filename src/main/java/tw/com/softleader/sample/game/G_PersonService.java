package tw.com.softleader.sample.game;


import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;
import tw.com.softleader.sample.game.G_PersonGame;
public class G_PersonService implements GenericService<G_Person> {

	@Override
	public G_Person getOne(Long pId) {
		G_PersonDao personDao = new G_PersonDao();
		return personDao.findOne(pId);
	}

	@Override
	public Collection<G_Person> getAll() {
		G_PersonDao personDao = new G_PersonDao();
		return personDao.findAll();
	}

	@Override
	public void insert(G_Person entity) {
		G_PersonDao personDao = new G_PersonDao();
		personDao.insert(entity);

	}

	@Override
	public void update(G_Person entity) {
		G_PersonDao personDao = new G_PersonDao();
		personDao.insert(entity);
	}

	@Override
	public void delete(Long pId) {
		G_PersonDao personDao = new G_PersonDao();
		personDao.delete(pId);
	}
	
	
	public  List<Game>getGameByPerson(Long pId){
		G_PersonDao personDao = new G_PersonDao();
	return personDao.getGameByPerson(pId);
	   
   }
	

}