package tw.com.triplei.protal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.dao.InsurerDao;
import tw.com.triplei.protal.entity.Insurer;

@Service
public class InsurerService {

	@Autowired
	private InsurerDao insurerDao;

	public List<Insurer> getAll() {
		return insurerDao.findAll();
	}

}
