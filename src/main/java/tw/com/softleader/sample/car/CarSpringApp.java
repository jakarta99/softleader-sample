package tw.com.softleader.sample.car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CarSpringApp {
	
	final static Logger log = LoggerFactory.getLogger(CarSpringApp.class);

	public static void main(String[] args) throws IOException {
		
//		ApplicationContext appContext
//			= new ClassPathXmlApplicationContext(new String[]{"/car.xml"});
//		
//		CarService carService = (CarService) appContext.getBean("carService");
		
		Resource resource = new ClassPathResource("/META-INF/license.txt");
		InputStream inputStream = resource.getInputStream();
//		inputStream
		File newFile = new File("/Users/bennychen/documents/license.txt");
		FileOutputStream outputStream = new FileOutputStream(newFile);
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		log.info("{}", resource.exists());
		log.info("{}", resource.getDescription());
		log.info("{}", resource.exists());
		log.debug("{}", "debug");
//		log.debug("{}",carService.getDefaultColor());
		
//		carService.getAll().forEach(c -> log.info("{}", c.toString()));
	}

}
