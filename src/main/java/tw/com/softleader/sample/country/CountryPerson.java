package tw.com.softleader.sample.country;

import java.util.Collection;

public interface CountryPerson<T> {

	public T findOne(Long id);

	public Collection<T> findAll();

	public void insert(T entity);

	public void update(T entity);

	public void delete(Long id);

}
