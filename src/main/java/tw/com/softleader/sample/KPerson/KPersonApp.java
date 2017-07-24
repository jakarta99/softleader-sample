package tw.com.softleader.sample.KPerson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KPersonApp {

	public static void main(String[] args) {
		Properties prop =  new Properties();
		try(InputStream input =KPersonApp.class.getResourceAsStream("/kperson.properties")){
			prop.load(input);
			KCompanyService kcCompanyService = (KCompanyServiceImpl) Class.forName(prop.getProperty("kCompanyService")).newInstance();
			((KCompanyServiceImpl) kcCompanyService).setDefaultName(prop.getProperty("kCompanyService.defaultName"));
			((KCompanyServiceImpl) kcCompanyService).setkCompanyDao((KCompanyDao) Class.forName(prop.getProperty("kCompanyService.kCompanyDao")).newInstance());
			System.out.println(kcCompanyService.getAll());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
