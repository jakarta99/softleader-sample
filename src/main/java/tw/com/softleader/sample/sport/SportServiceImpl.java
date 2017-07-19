package tw.com.softleader.sample.sport;

import java.util.Collection;

public class SportServiceImpl implements SportService {

	SportDao sportD = new SportDao();
	
	private String defaultname = "baseball"; 
	
	public void setDefaultname(String defaultname) {
		this.defaultname = defaultname;
	}

	public SportServiceImpl(){
		
	}
	
	public SportServiceImpl(String name){
		this.defaultname = name;
	}
	
	
	
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
