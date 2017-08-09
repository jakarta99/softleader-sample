package tw.com.triplei.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.portal.dao.ProductDao;
import tw.com.triplei.portal.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product getOne(Long id) {
		return productDao.findOne(id);
	}

	public List<Product> getAll() {
		return productDao.findAll();
	}

	public Product insert(Product product) {
		return productDao.save(product);
	}

	public Product update(Product product) {
		return productDao.save(product);
	}

	public boolean delete(Long id) {
		productDao.delete(id);
		if (productDao.findOne(id) != null) {
			return false;
		}
		return true;
	}

}
