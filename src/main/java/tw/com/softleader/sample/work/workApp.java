package tw.com.softleader.sample.work;

import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Jerry.lin on 2017/7/18.
 */
public class workApp {

    public static Logger log = LoggerFactory.getLogger(workApp.class);

    public static void main(String[] args) {





        WorkService service =(WorkService) new workContext().getBean("workService");


        final Collection<Work> works = service.getAll();
        for (Work work : works) {
            log.info(work.toString());
        }




    }
}
