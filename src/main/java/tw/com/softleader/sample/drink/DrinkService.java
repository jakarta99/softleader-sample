package tw.com.softleader.sample.drink;

import java.util.Arrays;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class DrinkService implements GenericService {

	private String[] drinks = {"coffee", "tea", "juice"};
	
	@Override
	public String[] getAll() {
		return drinks;
	}

	@Override
	public String getOne(int id) {
		return drinks[id];
	}

	@Override
	public void insert(String data) {
		String[] newArray = new String[drinks.length+1];
		for(int i = 0; i< drinks.length; i++) {
			newArray[i] = drinks[i];
		}
		newArray[newArray.length-1] = data;
		
		drinks = newArray;
		
	}

	@Override
	public void update(String data, int id) {
		drinks[id] = data;
	}

	@Override
	public void delete(String data) {
		
		String[] newArray = new String[drinks.length-1];
		int newArrayIndex = 0;
		for(int i = 0; i< drinks.length; i++) {
			if(!drinks[i].equals(data)) {
				newArray[newArrayIndex] = drinks[i];
				newArrayIndex++;
			}
		}
		
		drinks = newArray;
		
	}
	

	
	
	
	
}
