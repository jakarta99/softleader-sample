package tw.com.softleader.sample.notebook;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

/**
 * <br> 筆記型電腦服務
 * @author Frank
 *
 */
public class NotebookService implements GenericService<Notebook> {

	@Override
	public Notebook getOne(Long id) {
		return new NotebookDao().findOne(id);
	}
	
	public Collection<Notebook> getByWpersonId(Long wpersonId) {
		return new NotebookDao().findByWpersonId(wpersonId);
	}

	@Override
	public Collection<Notebook> getAll() {
		return new NotebookDao().findAll();
	}

	@Override
	public void insert(Notebook entity) {
		new NotebookDao().insert(entity);
	}

	@Override
	public void update(Notebook entity) {
		new NotebookDao().update(entity);
	}

	@Override
	public void delete(Long id) {
		new NotebookDao().delete(id);
	}

}
