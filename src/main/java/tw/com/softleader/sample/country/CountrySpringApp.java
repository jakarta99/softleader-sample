package tw.com.softleader.sample.country;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CountrySpringApp {

	public static void main(String[] args) {
		ApplicationContext appContext= new ClassPathXmlApplicationContext(new String[] {"/country.xml"});
		CountryService countryService=(CountryService)appContext.getBean("countryServiceImpl");
		
		System.out.println(countryService.getAll());
		
//		CountryServiceImpl countryServiceImpl=new CountryServiceImpl();
//		System.out.println(countryServiceImpl.getAll());
	}

}
