package tw.com.triplei.setting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.triplei.setting.entity.SettingInsurer;
import tw.com.triplei.setting.service.SettingInsurerService;

public class SettingInsurerApp {

	public static void main(String[] args) {
		
		
		// load application-context.xml 
		ApplicationContext applicationContext
			= new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		
		// try getAll , sysout.
		SettingInsurerService service 
			= (SettingInsurerService) applicationContext.getBean("settingInsurerService");
		
		for(SettingInsurer insurer:service.getAll()) {
			System.out.println(insurer);
		}
	}
	
	
}
