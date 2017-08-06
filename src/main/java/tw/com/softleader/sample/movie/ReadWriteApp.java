package tw.com.softleader.sample.movie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tw.com.softleader.sample.commons.DataSourceUtil;

public class ReadWriteApp {

	public static void main(String[] args) throws IOException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "/movie.xml" });
		DataSourceUtil dataSource = (DataSourceUtil) ctx.getBean("dataSource");

		ReadAndWriteFile readAndWriteFile = (ReadAndWriteFile) ctx.getBean("readAndWriteFile");
		String toStringfrom = readAndWriteFile.getCopyFilefrom();
		String toStringto = readAndWriteFile.getCopyfileto();
		InputStream is = ReadWriteApp.class.getResourceAsStream(toStringfrom);
		FileOutputStream os = new FileOutputStream(toStringto);
		int data;
		while ((data = is.read()) != -1) {
			os.write(data);
		}
		os.close();
		is.close();
	}
}
