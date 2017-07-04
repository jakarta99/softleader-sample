package tw.com.softleader.sample.commons;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSourceUtil {
	
	private Logger log = Logger.getLogger(DataSourceUtil.class);
	
	private static DataSourceUtil instance = null;
	
	private static DataSource datasource = null;
	
	public DataSourceUtil() {
		
		log.debug("begin to initialize datasource...");
		
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:postgresql://localhost:5432/testdb");
		ds.setUsername("postgres");
		ds.setPassword("postgres");
		
		ds.setMinIdle(50);
		ds.setMaxIdle(100);
		
		log.debug("end initialize datasource...");
		
		datasource = ds;
	}
	
	public static DataSourceUtil getInstance() {
		if( instance == null ) {
			instance = new DataSourceUtil();
		}
		return instance;
	}
	

	public DataSource getDataSource() {
		return datasource;
	}
	
	
	
}
