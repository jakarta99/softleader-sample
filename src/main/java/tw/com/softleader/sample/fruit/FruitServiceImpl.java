package tw.com.softleader.sample.fruit;

import java.util.Collection;

public class FruitServiceImpl implements FruitService {
//
//
//	private String defaultColor = "yellow";
//	
//	public FruitServiceImpl() {
//		
//	}
//	
//	public FruitServiceImpl(String color) {
//		this.defaultColor = color;
//	}
	
	@Override
	public Fruit getOne(Long id) {
		FruitDao dao = new FruitDao();
		return dao.findOne(id);
	}

	@Override
	public Collection<Fruit> getAll() {
//		System.out.println(defaultColor);
		FruitDao dao = new FruitDao();
		
		return dao.findAll();
	}

	@Override
	public void insert(Fruit entity) {
		FruitDao dao = new FruitDao();
		dao.insert(entity);
		
	}

	@Override
	public void update(Fruit entity) {
		FruitDao dao = new FruitDao();
		dao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		FruitDao dao = new FruitDao();
		dao.delete(id);
		
	}

}
