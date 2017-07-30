package tw.com.softleader.sample.books;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.softleader.sample.commons.DataSourceUtil;

public class CopyFileApp {
	
	public static void main(String[] args) throws SQLException, IOException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"/book.xml"});
		DataSourceUtil dataSource = (DataSourceUtil) ctx.getBean("dataSource");
		System.out.println(dataSource.getDataSource().getConnection());
		
		CopyFile copyFile = (CopyFile) ctx.getBean("copyFile");
		String toStringfrom = copyFile.toStringfrom();
		String toStringto = copyFile.toStringto();		
		InputStream is = CopyFileApp.class.getResourceAsStream(toStringfrom);
		FileOutputStream os = new FileOutputStream(toStringto);
		int data;
		while((data=is.read())!= -1){
			os.write(data);
		}
		os.close();
		is.close();
	}
}
