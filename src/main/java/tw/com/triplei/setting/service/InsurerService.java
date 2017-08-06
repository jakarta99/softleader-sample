package tw.com.triplei.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.entity.Insurer;
import tw.com.triplei.setting.dao.InsurerDao;

@Service
public class InsurerService {

	@Autowired
	private InsurerDao insurerDao;

	public List<Insurer> getAll() {
		return insurerDao.findAll();
	}

}
