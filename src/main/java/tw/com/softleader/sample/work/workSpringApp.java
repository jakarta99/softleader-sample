package tw.com.softleader.sample.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Jerry.lin on 2017/7/18.
 */
public class workSpringApp {

    public static Logger log = LoggerFactory.getLogger(workSpringApp.class);
//
//    @Autowired
//    WorkService workService;

    public static void main(String[] args) {

        try {
            ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"work.xml"});
            final WorkService workService = (WorkService) appContext.getBean("workService");



            final Collection<Work> works = workService.getAll();
            for (Work work : works) {
                log.info(work.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
