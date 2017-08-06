package tw.com.triplei.protal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.dao.GiftDao;
import tw.com.triplei.protal.entity.Gift;

@Service
public class GiftService {

	@Autowired
	private GiftDao giftDao;

	public List<Gift> getAll() {
		return giftDao.findAll();
	}
}
