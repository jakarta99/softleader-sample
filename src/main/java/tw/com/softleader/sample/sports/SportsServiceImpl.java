package tw.com.softleader.sample.sports;

import java.util.Collection;

public class SportsServiceImpl implements SportsService {

	
//	SportDao sportD = new SportDao();
	
	private SportDao sportDao ;
	
	

	public SportDao getSportDao() {
		return sportDao;
	}

	public void setSportDao(SportDao sportDao) {
		this.sportDao = sportDao;
	}

	@Override
	public Sport getOne(Long id) {
		return sportDao.findOne(id);
	}

	@Override
	public Collection<Sport> getAll() {
		return sportDao.findAll();
	}

	@Override
	public void insert(Sport entity) {
		sportDao.insert(entity);

	}

	@Override
	public void update(Sport entity) {
		sportDao.update(entity);

	}

	@Override
	public void delete(Long id) {
		sportDao.delete(id);

	}

}
