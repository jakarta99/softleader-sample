package tw.com.triplei.protal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.dao.SettingGiftDao;
import tw.com.triplei.protal.entity.Gift;

@Service
public class SettingGiftService {

	@Autowired
	private SettingGiftDao settingGiftDao;

	public List<Gift> getAll() {
		return settingGiftDao.findAll();
	}
}
