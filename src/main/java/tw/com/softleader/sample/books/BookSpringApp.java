package tw.com.softleader.sample.books;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class BookSpringApp {

	public static void main(String[] args) {
		
		ApplicationContext appContext = 
				new ClassPathXmlApplicationContext(new String[]{"/book.xml"});
		
		BookServiceImpl bookService = (BookServiceImpl) appContext.getBean("bookService");
		
//		BookServiceImpl bookService = new BookServiceImpl();
		bookService.getAll();
		
		
	}

}
