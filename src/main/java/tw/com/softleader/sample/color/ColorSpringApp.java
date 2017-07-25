package tw.com.softleader.sample.color;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ColorSpringApp {
	
	public static void main(String[] args) {
		
		ApplicationContext appContext
			= new ClassPathXmlApplicationContext(new String[]{"/color.xml"});
		
		ColorService colorService = (ColorService) appContext.getBean("colorService");
		
		//colorService.getAll();
		System.out.println(colorService.getAll());
		

	}

}
