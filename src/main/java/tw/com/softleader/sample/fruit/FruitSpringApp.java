package tw.com.softleader.sample.fruit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FruitSpringApp {
	
	public static void main(String[] args) {
		
		ApplicationContext appcontext = 
				new ClassPathXmlApplicationContext(new String[] {"/fruit.xml"});
		
		FruitService fruitService =(FruitService) appcontext.getBean("fruitService");
		
		
		
		
	}

}
