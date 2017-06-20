package tw.com.softleader.sample.color;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import tw.com.softleader.sample.commons.GenericService;
import tw.com.softleader.sample.country.Country;

/**
 * 
 * @author Hilda
 *
 */
public class ColorService implements GenericService<Color> {
	
	private List<Color> colors = new ArrayList<Color>();

	public ColorService() {
		Color color1 = new Color();
		color1.setId(1);
		color1.setName("Red");
		color1.setCode("#FF0000");

		Color color2 = new Color();
		color2.setId(2);
		color2.setName("Black");
		color2.setCode("#FFFFFF");
		
		colors.add(color1);
		colors.add(color2);
	}

	@Override
	public Color getOne(int id) {
		// TODO Auto-generated method stub
		return colors.get(id);
	}

	@Override
	public List<Color> getAll() {
		// TODO Auto-generated method stub
		return colors;
	}

	@Override
	public void insert(Color data) {
		// TODO Auto-generated method stub
		Color newColor = new Color();
		newColor.setId(data.getId());
		newColor.setName(data.getName());
		newColor.setCode(data.getCode());
		
		colors.add(newColor);
	}

	@Override
	public void update(Color data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int data) {
		// TODO Auto-generated method stub
		
	}


}
