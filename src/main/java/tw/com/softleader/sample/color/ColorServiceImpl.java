package tw.com.softleader.sample.color;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author Hilda
 *
 */
public class ColorServiceImpl implements ColorService {
	private Logger log = Logger.getLogger(this.getClass());
	
	private String stringValue = "yellow";  
    private int number = 0;  
    private long numberLong = 0L;
    private boolean booleanValue = false;
    private String[] stringValues;
    private List<Color> colorList;
 
	
	ColorDao colorDao = new ColorDao();
	
	public ColorServiceImpl() {
		
	}
	
	public ColorServiceImpl(String stringValue, int number, long numberLong, Boolean booleanValue) {
		this.stringValue = stringValue;
		this.number = number;
		this.numberLong = numberLong;
		this.booleanValue = booleanValue;
	}

	@Override
	public Color getOne(Long id) {
		return colorDao.findOne(id);
	}


	@Override
	public Collection<Color> getAll() {	
//		log.info("getAll");
		log.info(stringValue);
		log.info(number);
		log.info(numberLong);
		log.info(booleanValue);
		
		for (String list : stringValues){
			log.info(list);
		}
		
		for (Color list: colorList){
			log.info(list.toString());
		}
				
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
	
	

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setNumberLong(long numberLong) {
		this.numberLong = numberLong;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	
	public void setStringValues(String[] stringValues) {
		this.stringValues = stringValues;
	}

	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}

}
