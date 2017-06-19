package tw.com.softleader.sample.drink;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class DrinkService implements GenericService {

	@Override
	public String[] getAll() {
		String[] drinks = {"coffee", "tea", "juice"};
		return drinks;
	}
	

	
	
	
	
}
