package tw.com.triplei.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.entity.Gift;
import tw.com.triplei.setting.dao.SettingGiftDao;

@Service
public class SettingGiftService {

	@Autowired
	private SettingGiftDao settingGiftDao;

	public List<Gift> getAll() {
		return settingGiftDao.findAll();
	}
}
