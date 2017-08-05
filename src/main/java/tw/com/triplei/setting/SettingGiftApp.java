package tw.com.triplei.setting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.triplei.protal.entity.Gift;
import tw.com.triplei.setting.service.SettingGiftService;

public class SettingGiftApp {

	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		SettingGiftService settingGiftService = (SettingGiftService) application.getBean("settingGiftService");

		for (Gift gift : settingGiftService.getAll()) {
			System.out.println(gift);
		}

	}
}
