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
		color1.setId(1L);
		color1.setName("Red");
		color1.setCode("#FF0000");

		Color color2 = new Color();
		color2.setId(2L);
		color2.setName("Black");
		color2.setCode("#FFFFFF");

		Color color3 = new Color();
		color3.setId(3L);
		color3.setName("Green");
		color3.setCode("#00FF00");

		colors.add(color1);
		colors.add(color2);
		colors.add(color3);
	}

	@Override
	public Color getOne(Long id) {
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i).getId() == colors.get(id).getId()) {
				return colors.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Color> getAll() {
		return colors;
	}

	@Override
	public void insert(Color data) {
		colors.add(data);
	}

	@Override
	public void update(Color data) {
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i).getId() == data.getId()) {
				colors.get(i).setName(data.getName());
				colors.get(i).setCode(data.getCode());
			}
		}
	}

	@Override
	public void delete(Long data) {
		// Watch out :Concurrent Modification Exception
//		for (int i = 0; i < colors.size(); i++) {
//			if (colors.get(i).getId() == data) {
//				colors.remove(i);
//			}
//		}
		
		List<Color> removeColor = new ArrayList<Color>();
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i).getId() == data) {
				removeColor.add(colors.get(i));
			}
		}
		colors.removeAll(removeColor);
	}
}
