package tw.com.softleader.sample.sports;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("sport.xml");
		
		SportsServiceImpl service = (SportsServiceImpl) ctx.getBean("sportServiceImpl");

		
		service.getAll();
	}

}
