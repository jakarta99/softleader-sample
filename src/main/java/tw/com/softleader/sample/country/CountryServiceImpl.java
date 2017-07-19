package tw.com.softleader.sample.country;

import java.util.Collection;

public class CountryServiceImpl implements CountryService {
	CountryDao countryDao = new CountryDao();
	
	private String defaultSize="Small";
	
	private CountryDao dao;
	
//	public CountryServiceImpl(){
//		
//	}
//	
//	public CountryServiceImpl(String size){
//		this.defaultSize=size;
//	}
	

	
	public void setDefaultSize(String defaultSize){
		this.defaultSize=defaultSize;
	}
	
	public void setDao(CountryDao dao) {
		this.dao = dao;
	}

	@Override
	public Country getOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Collection<Country> getAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Country entity) {
		dao.insert(entity);
	}

	@Override
	public void update(Country entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
