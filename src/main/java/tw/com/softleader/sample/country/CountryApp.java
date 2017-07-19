package tw.com.softleader.sample.country;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CountryApp {

	public static void main(String[] args) {
		
		try{
			Properties prop=new Properties();
			InputStream is = CountryApp.class.getClass().getResourceAsStream("/country.properties");
//			InputStream is1=CountryApp.class.getClassLoader().getResourceAsStream("/country.properties");

			prop.load(is);
			
			CountryService countryService=(CountryService)Class.forName(prop.getProperty("countryService")).newInstance();
			countryService.getAll();
			
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
