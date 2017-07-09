package tw.com.softleader.sample.game;

import java.util.Collection;
import java.util.List;

public interface G_PersonGame<T> {

	public T findOne(Long id);
	
	public Collection<T> findAll();
	
	public void insert(T entity);
	
	public void update(T entity);
	
	public void delete(Long id);
	
	List<Game> getGameByPerson(Long pId);
}