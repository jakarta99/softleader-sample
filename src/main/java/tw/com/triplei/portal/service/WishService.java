package tw.com.triplei.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.portal.dao.WishDao;
import tw.com.triplei.portal.entity.Wish;

@Service
public class WishService {

	@Autowired
	private WishDao wishDao;

	public List<Wish> getAll() {
		return wishDao.findAll();
	}

	public Wish gatOne(Long id) {
		return wishDao.getOne(id);
	}

	public Wish insert(Wish wish) {
		return wishDao.save(wish);
	}

	public Wish update(Wish wish) {
		return wishDao.save(wish);
	}

	public boolean delete(Long id) {
		wishDao.delete(id);
		if (wishDao.findOne(id) != null) {
			return false;
		}
		return true;
	}
}
