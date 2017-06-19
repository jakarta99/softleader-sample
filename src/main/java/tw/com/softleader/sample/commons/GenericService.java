package tw.com.softleader.sample.commons;

import java.util.Collection;

public interface GenericService<T> {

	public T getOne(int id);
	
	public Collection<T> getAll();
	
	public void insert(T data);
	
	public void update(T data);
	
	public void delete(T data);
	
}
