package tw.com.softleader.sample.color;

import java.util.Collection;
import tw.com.softleader.sample.commons.GenericService;

public class HCompanyService implements GenericService<HCompany>{
	
	HCompanyDao dao = new HCompanyDao();

	@Override
	public HCompany getOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Collection<HCompany> getAll() {
		return dao.findAll();
	}

	@Override
	public void insert(HCompany entity) {
		dao.insert(entity);
		
	}

	@Override
	public void update(HCompany entity) {
		dao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
