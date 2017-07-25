package tw.com.softleader.sample.work;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Jerry.lin on 2017/7/18.
 */
public class workContext {


    public Object getBean (String className) {
        Properties prop = new Properties();
        WorkServiceImpl workService = null;
        try (final InputStream input = ClassLoader.getSystemResourceAsStream("work.properties")){
            prop.load(input);
            workService = (WorkServiceImpl) Class.forName(prop.getProperty(className)).newInstance();
//            workService.setWorkDao(new WorkDao());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workService;
    }
}
