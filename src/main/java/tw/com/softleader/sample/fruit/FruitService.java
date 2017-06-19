package tw.com.softleader.sample.fruit;

import java.util.Arrays;

import tw.com.softleader.sample.commons.GenericService;

public class FruitService implements GenericService {
	private String[] fruits = { "西瓜", "蘋果", "芭樂"};

	public String[] getAll() {
		return fruits;
	}

	@Override
	public String getOne(int id) {
		return fruits[id];
	}

	@Override
	public void insert(String data) {
		fruits = Arrays.copyOf(fruits, fruits.length+1);
		fruits[fruits.length-1] = data;
		
	}

	@Override
	public void update(String data, int id) {
		fruits[id] = data;
		
	}

	@Override
	public void delete(String data) {
//		fruits = Arrays.copyOf(fruits, fruits.length-1);
		
	}
}
