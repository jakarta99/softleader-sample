package tw.com.triplei.setting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.triplei.protal.entity.Insurer;
import tw.com.triplei.protal.service.InsurerService;

public class InsurerApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		InsurerService insurerSurvice = (InsurerService) applicationContext.getBean("insurerService");

		for (Insurer insurer : insurerSurvice.getAll()) {
			System.out.println(insurer);
		}
	}

}
