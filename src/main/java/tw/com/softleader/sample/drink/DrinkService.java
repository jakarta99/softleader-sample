package tw.com.softleader.sample.drink;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

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
		drinks = ArrayUtils.add(drinks, data);
	}

	@Override
	public void update(String data, int id) {
		drinks[id] = data;
	}

	@Override
	public void delete(String data) {
		drinks = ArrayUtils.removeElement(drinks, data);
	}
	

	
	
	
	
}
