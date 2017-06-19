package tw.com.softleader.sample.color;

import org.apache.commons.lang3.ArrayUtils;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * @author Hilda
 *
 */
public class ColorService implements GenericService {

	private String[] colors = { "Red", "Yellow", "Blue" };

	@Override
	public String[] getAll() {
		return colors;
	}

	@Override
	public String getOne(int id) {
		return colors[id];
	}

	@Override
	public void insert(String data) {
//		String[] colorClone = new String[colors.length + 1];
//		colorClone = colors.clone();
//		colorClone[colorClone.length - 1] = data;
//		colors = colorClone;
		
		colors = ArrayUtils.add(colors, data);
		//ArrayUtils.add(colors, data);
	}

	@Override
	public void update(String data, int id) {
		colors[id] = data;
	}

	@Override
	public void delete(String data) {
//		String[] colorDelete = new String[colors.length - 1];
//		int cnt = 0; 
//		for (int i = 0; i < colorDelete.length; i++) {
//			if (!colors[i].equals(data)) {
//				colorDelete[cnt] = colors[i];
//				cnt++; 
//			}
//		}
//		colors = colorDelete;
		
		colors = ArrayUtils.removeElement(colors, data);
		//ArrayUtils.removeElement(colors, data);
	}
}
