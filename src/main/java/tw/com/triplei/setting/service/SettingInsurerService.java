package tw.com.triplei.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.setting.dao.SettingInsurerDao;
import tw.com.triplei.setting.entity.SettingInsurer;

@Service
public class SettingInsurerService {
	
	@Autowired
	private SettingInsurerDao settingInsurerDao;

	public List<SettingInsurer> getAll() {
		return settingInsurerDao.findAll();
	}
	
	
}
