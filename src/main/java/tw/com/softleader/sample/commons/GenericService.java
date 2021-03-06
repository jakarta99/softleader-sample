package tw.com.softleader.sample.commons;

import java.util.Collection;

public interface GenericService<T> {

	public T getOne(Long id);

	public Collection<T> getAll();

	public void insert(T entity);

	public void update(T entity);

	public void delete(Long id);

}
