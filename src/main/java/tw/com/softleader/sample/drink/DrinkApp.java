package tw.com.softleader.sample.drink;

import java.util.Properties;

public class DrinkApp {

	public static void main(String[] args) {
		
		// 1. classpath 載入 drink.properties
		
		Properties prop = new Properties(); // TODO
		
		// 2. 
		try {
			DrinkService drinkService =
					(DrinkService) Class.forName(prop.getProperty("drinkService")).newInstance();
			
			
			
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
		}
		
		
		
		
	}
	
	
}
