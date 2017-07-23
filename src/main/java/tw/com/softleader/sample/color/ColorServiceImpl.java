package tw.com.softleader.sample.color;

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
	
//	private String defaultColor = "yellow";
//	private Integer defaultNumber = 0;
	
	private String stringValue = "yellow";  
    private int number = 0;  
    private long numberLong = 0L;
    private boolean booleanValue = false;
    
    private int[] numbers;  
    private String[] stringValues;  
    private List<String> stringLists; 
	
	ColorDao colorDao = new ColorDao();
	
	public ColorServiceImpl() {
		
	}
	
	public ColorServiceImpl(String color, int number, long numberLong, Boolean booleanValue) {
		this.stringValue = color;
		this.number = number;
		this.numberLong = numberLong;
		this.booleanValue = booleanValue;
	}
	
	
	public void setDefaultColor(String defaultColor) {
		this.stringValue = defaultColor;
	}
	
	@Override
	public Color getOne(Long id) {
		return colorDao.findOne(id);
	}


	@Override
	public Collection<Color> getAll() {	
		log.info("getAll");
		log.info(stringValue);
		log.info(number);
		log.info(numberLong);
		log.info(booleanValue);
		
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
