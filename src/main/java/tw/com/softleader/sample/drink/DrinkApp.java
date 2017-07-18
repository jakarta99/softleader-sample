package tw.com.softleader.sample.drink;

import java.io.IOException;
import java.util.Properties;

public class DrinkApp {

	public static void main(String[] args) {
		
		try {
			// 1. classpath 載入 drink.properties
	
			Properties prop = new Properties(); // TODO
	
			prop.load(ClassLoader.getSystemResourceAsStream("drink.properties"));
	
			// 2.
		
			DrinkService drinkService = (DrinkService) Class.forName(prop.getProperty("drinkService")).newInstance();

			drinkService.getAll();

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
