package tw.com.softleader.sample.books;

import java.util.Properties;



public class BookApp {
	
	public static void main(String[] args){
		
		
		Properties prop = new Properties();
		
		try {
			BookService bookService = (BookService) Class.forName("bookService").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
