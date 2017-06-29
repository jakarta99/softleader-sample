package tw.com.softleader.sample.sports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

public class SportsService implements GenericService<Sport> {

	SportDao sportD = new SportDao();

	@Override
	public Sport getOne(Long id) {
		return sportD.findOne(id);
	}

	@Override
	public Collection<Sport> getAll() {
		return sportD.findAll();
	}

	@Override
	public void insert(Sport entity) {
		sportD.insert(entity);

	}

	@Override
	public void update(Sport entity) {
		sportD.update(entity);

	}

	@Override
	public void delete(Long id) {
		sportD.delete(id);

	}

}
