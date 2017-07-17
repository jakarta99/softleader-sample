package tw.com.softleader.sample.car;

import java.util.Collection;
import java.util.Optional;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 公司的服務
 * @author Benny Chen
 *
 */
public class JCompanyService implements GenericService<JCompany> {

	@Override
	public JCompany getOne(Long id) {
		JCompanyDao jCompanyDao = new JCompanyDao();
		
		Optional<JCompany> jCompany = Optional.ofNullable(jCompanyDao.findOne(id));
		
		jCompany.ifPresent(c -> {
			JPersonDao jPersonDao = new JPersonDao();
			CarDao carDao = new CarDao();
			
			Collection<JPerson> jPeople = jPersonDao.findByJCompanyId(id);
			jPeople.forEach(p -> p.setCars(carDao.findByJPersonId(p.getId())));
			c.setjPeople(jPeople);
		});
		
		return jCompany.isPresent() ? jCompany.get() : new JCompany();
	}

	@Override
	public Collection<JCompany> getAll() {
		JCompanyDao jCompanyDao = new JCompanyDao();
		
		Collection<JCompany> jCompanies = jCompanyDao.findAll();
		
		if(jCompanies != null && jCompanies.size() > 0) {
			JPersonDao jPersonDao = new JPersonDao();
			CarDao carDao = new CarDao();
			
			jCompanies.forEach(c -> {
				Collection<JPerson> jPeople = jPersonDao.findByJCompanyId(c.getId());
				jPeople.forEach(p -> p.setCars(carDao.findByJPersonId(p.getId())));
				c.setjPeople(jPeople);
			});
		}
		
		return jCompanies;
	}

	@Override
	public void insert(JCompany entity) {
		JCompanyDao jCompanyDao = new JCompanyDao();
		jCompanyDao.insert(entity);
		
		Collection<JPerson> jPeople = entity.getjPeople();
		if (jPeople != null && jPeople.size() > 0) {
			JPersonService jPersonService = new JPersonService();
			jPeople.forEach(p -> {
				p.setjCompanyId(entity.getId());
				jPersonService.insert(p);
			});
		}
	}

	@Override
	public void update(JCompany entity) {
		JCompanyDao jCompanyDao = new JCompanyDao();
		jCompanyDao.update(entity);
		
		Collection<JPerson> jPeople = entity.getjPeople();
		if (jPeople != null && jPeople.size() > 0) {
			JPersonService jPersonService = new JPersonService();
			jPeople.forEach(p -> {
				if (p.getId() != null) {
					jPersonService.update(p);
				}else {
					p.setjCompanyId(entity.getId());
					jPersonService.insert(p);
				}
			});
		}
	}

	@Override
	public void delete(Long id) {
		JCompany jCompany = getOne(id);
		
		Collection<JPerson> jPeople = jCompany.getjPeople();
		
		if (jPeople != null && jPeople.size() > 0) {
			JPersonService jPersonService = new JPersonService();
			jPeople.forEach(p -> jPersonService.delete(p.getId()));
		}
		
		JCompanyDao jCompanyDao = new JCompanyDao();
		jCompanyDao.delete(id);
	}
	
}
