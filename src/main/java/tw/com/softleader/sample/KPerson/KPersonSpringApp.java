package tw.com.softleader.sample.KPerson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KPersonSpringApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "/kperson.xml" });
		KCompanyService kcService = (KCompanyService) applicationContext.getBean("kCompanyService");
		System.out.println(kcService.getAll());

	}

}
