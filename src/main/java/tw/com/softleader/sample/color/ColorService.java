package tw.com.softleader.sample.color;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import tw.com.softleader.sample.commons.GenericService;
import tw.com.softleader.sample.country.Country;
import tw.com.softleader.sample.drink.DrinkDao;

/**
 * 
 * @author Hilda
 *
 */
public class ColorService implements GenericService<Color> {
	
	private List<Color> colors = new ArrayList<Color>();
	ColorDao colorDao = new ColorDao();

//	public ColorService() {
//		Color color1 = new Color();
//		color1.setId(1L);
//		color1.setName("Red");
//		color1.setCode("#FF0000");
//
//		Color color2 = new Color();
//		color2.setId(2L);
//		color2.setName("Black");
//		color2.setCode("#FFFFFF");
//
//		Color color3 = new Color();
//		color3.setId(3L);
//		color3.setName("Green");
//		color3.setCode("#00FF00");
//
//		colors.add(color1);
//		colors.add(color2);
//		colors.add(color3);
//	}

	@Override
	public Color getOne(Long id) {
		return colorDao.findOne(id);
	}

	@Override
	public Collection<Color> getAll() {	
		return colorDao.findAll();
	}

	@Override
	public void insert(Color data) {
		colorDao.insert(data);
	}

	@Override
	public void update(Color data) {
		colorDao.update(data);
	}

	@Override
	public void delete(Long data) {
		colorDao.delete(data);
	}
}
