package tw.com.softleader.sample.sport;

import java.io.IOException;
import java.util.Properties;

public class SportApp {

	public static void main(String[] args) {

		Properties prop = new Properties();
		try {
			
			
			prop.load(ClassLoader.getSystemResourceAsStream("sport.properties"));
			
			SportService sportService = (SportService) Class.forName(prop.getProperty("sportService")).newInstance();
		
		
			sportService.getAll();
			
			
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