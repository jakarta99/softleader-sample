package tw.com.softleader.sample.work;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public interface WorkDao extends JpaRepository<Work, Long> {

}
