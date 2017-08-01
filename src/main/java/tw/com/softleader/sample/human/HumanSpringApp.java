package tw.com.softleader.sample.human;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class HumanSpringApp {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "/human.xml" });

//		HumanService humanService = (HumanService) appContext.getBean("HumanService");
		
//		System.out.println(humanService.getAll());
		
		Resource resource = appContext.getResource("jar:file:/C:/Users/Samuel/.m2/repository/org/apache/commons/commons-lang3/3.6/commons-lang3-3.6.jar!/META-INF/LICENSE.txt");
		System.out.println(resource.getURI());
		InputStream inputStream = resource.getInputStream();
		byte[] fileBytes = new byte[inputStream.available()];
		inputStream.read(fileBytes);
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/java/tw/com/softleader/sample/human/testCopyFile.txt"));) {
			fileOutputStream.write(fileBytes);
		}
		
		ClassLoader.getSystemResourceAsStream("/META-INF/LICENSE");
		
		inputStream.read(new byte[inputStream.available()]);
		
//		Class<?> humanClass = Class.forName("tw.com.softleader.sample.human.Human");
//		
//		Class<?>[] humanParms = new Class[2];
//		humanParms[0] = String.class;
//		humanParms[1] = String.class;
//
//		Constructor<?> humanConstructor = humanClass.getConstructor(humanParms);
//		
//		Object[] humanObj = new Object[2];
//		humanObj[0] = "Samuel";
//		humanObj[1] = "Boy";
//		
//		Human human = (Human)humanConstructor.newInstance(humanObj);
//		System.out.println("Before : " + human);
//		
//		Class<?>[] nameSetterParm = new Class[1];
//		nameSetterParm[0] = String.class;
//		
//		Method setterMethod = humanClass.getMethod("setName", nameSetterParm);
//		Object[] setNameParm = new Object[1];
//		setNameParm[0] = "Allen";
//		setterMethod.invoke(human, setNameParm);
//		
//		System.out.println("After : " + human);
	}

}
