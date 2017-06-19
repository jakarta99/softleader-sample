package tw.com.softleader.sample.drink;

import java.util.ArrayList;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class DrinkService implements GenericService<Drink> {

	private List<Drink> drinks = new ArrayList<Drink>();
	
	public DrinkService() {

		Drink drink1 = new Drink();
		drink1.setId(1);
		drink1.setName("coffee");
		drink1.setColor("black");
		
		Drink drink2 = new Drink();
		drink2.setId(2);
		drink2.setName("juice");
		drink2.setColor("orange");
		
		Drink drink3 = new Drink();
		drink3.setId(3);
		drink3.setName("tea");
		drink3.setColor("brown");
		
		drinks.add(drink1);
		drinks.add(drink2);
		drinks.add(drink3);
	}
	
	
	@Override
	public Drink getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Drink> getAll() {
		
		return drinks;
	}

	@Override
	public void insert(Drink data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Drink data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
}
