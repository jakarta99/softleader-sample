package tw.com.softleader.sample.movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class MovieSpringApp {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(new String [] {"/movie.xml"});
		
		MovieService movieService = (MovieService)appContext.getBean("movieService");//
		movieService.getAll();
	}
}
