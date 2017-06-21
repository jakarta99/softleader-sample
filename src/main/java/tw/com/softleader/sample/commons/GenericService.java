package tw.com.softleader.sample.commons;

import java.util.List;

public interface GenericService<T> {

	public T getOne(int id);

	public List<T> getAll();

	public void insert(T data);

	public void update(T data);

	public void delete(int id);

}
