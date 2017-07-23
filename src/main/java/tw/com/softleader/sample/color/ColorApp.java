package tw.com.softleader.sample.color;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class ColorApp {
	
	public static void main(String[] args){
		
		// 1. classpath 載入 drink.properties
		
		Properties prop = new Properties(); 
		
		try {
			prop.load(ColorApp.class.getClassLoader().getResourceAsStream("color.properties"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ColorService colorService;
		try {
			
			colorService = (ColorService) Class.forName(prop.getProperty("colorService")).newInstance();

			colorService.getAll();
			
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
