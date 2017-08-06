package tw.com.triplei.setting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.triplei.setting.entity.SettingInsurer;
import tw.com.triplei.setting.service.SettingInsurerService;

public class SettingInsurerApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		SettingInsurerService settingInsurerService = (SettingInsurerService) applicationContext.getBean("settingInsurerService");

		for (SettingInsurer settingInsurer : settingInsurerService.getAll()) {
			System.out.println(settingInsurer);
		}
	}

}
