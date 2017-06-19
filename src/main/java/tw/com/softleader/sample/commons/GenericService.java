package tw.com.softleader.sample.commons;

public interface GenericService {

	public String getOne(int id);
	
	public String[] getAll();
	
	public void insert(String data);
	
	public void update(String data, int id);
	
	public void delete(String data);
	
}
