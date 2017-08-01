package tw.com.softleader.sample.car;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CarApp {

	public static void main(String[] args) {
		
//		Logger logger = LoggerFactory.getLogger(CarApp.class);
		Logger logger = Logger.getLogger(CarApp.class);
		
		try {
			// 1. classpath 載入 car.properties
			Properties prop = new Properties(); // TODO
	
			prop.load(ClassLoader.getSystemResourceAsStream("car.properties"));
	
			// 2.
			CarService carService = (CarService) Class.forName(prop.getProperty("carService")).newInstance();
			
			carService.getAll().forEach(c -> logger.debug(c.toString()));
//			logger.debug();
			

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
