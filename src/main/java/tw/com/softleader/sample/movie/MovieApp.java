package tw.com.softleader.sample.movie;

import java.io.IOException;
import java.util.Properties;


public class MovieApp {

	public static void main(String[] args) {

		try {
			Properties prop = new Properties();
			prop.load(ClassLoader.getSystemResourceAsStream("movie.properties"));
			MovieService movieService = (MovieService) Class.forName(prop.getProperty("movieService")).newInstance();
			movieService.getAll();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
