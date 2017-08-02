package tw.com.softleader.sample.color;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;


public class ColorSpringApp {
	
	public static void main(String[] args) {
	
		ApplicationContext appContext
			= new ClassPathXmlApplicationContext(new String[]{"/color.xml"});
		
		ColorService colorService = (ColorService) appContext.getBean("colorService");
		System.out.println(colorService.getAll());

		String filePath = "classpath:META-INF/LICENSE.txt";
		Resource template = appContext.getResource(filePath);
		try {
			// check the file
			if(template.exists()){
				System.out.println("the file template is exists");
				
				InputStream input = template.getInputStream();
				OutputStream output = new FileOutputStream("C:\\Users\\output_LICENSE.txt");
				
				int byteValue;
				while((byteValue = input.read()) != -1){
					output.write(byteValue);
				}
				
				input.close();
				output.close();
			} else {
				throw new FileNotFoundException("Could not find file path:" + filePath);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
