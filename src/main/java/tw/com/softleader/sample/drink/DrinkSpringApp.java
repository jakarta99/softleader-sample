package tw.com.softleader.sample.drink;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrinkSpringApp {

	public static void main(String[] args) {
		
		ApplicationContext appContext
			= new ClassPathXmlApplicationContext(new String[]{"/drink.xml"});
		
		
		DrinkService drinkService = (DrinkService) appContext.getBean("drinkService");
		
		drinkService.getAll();
		
		
	}

}
