package tw.com.softleader.sample.commons;

import java.util.Collection;

public interface GenericDao<T> {

	public T findOne(Long id);
	
	public Collection<T> findAll();
	
	public void insert(T entity);
	
	public void update(T entity);
	
	public void delete(Long id);
	
	
}
