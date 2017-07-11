package tw.com.softleader.sample.commons;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {
	
	private Logger log = Logger.getLogger(DataSourceUtil.class);
	
	private static DataSourceUtil instance = null;
	
	private static DataSource datasource = null;
	
	public DataSourceUtil() {
		
		log.debug("begin to initialize datasource...");
		
//		BasicDataSource ds = new BasicDataSource();
//		ds.setUrl("jdbc:postgresql://localhost:5432/testdb");
//		ds.setUsername("postgres");
//		ds.setPassword("postgres");
//		
//		ds.setMinIdle(50);
//		ds.setMaxIdle(100);
		
		// change to HikariCP. https://brettwooldridge.github.io/HikariCP/
		
		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:postgresql://localhost:5433/testdb");
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
		config.setUsername("postgres");
		config.setPassword("postgres");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		config.setMinimumIdle(50);
		config.setMaximumPoolSize(100);		
		
		HikariDataSource ds = new HikariDataSource(config);
		
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
