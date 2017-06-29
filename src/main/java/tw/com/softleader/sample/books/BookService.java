package tw.com.softleader.sample.books;

import java.util.Collection;
import tw.com.softleader.sample.commons.GenericService;

public class BookService implements GenericService<Book> {
	
	BookDao bookDao = new BookDao();
	
	@Override
	public Book getOne(Long id) {
		
		return bookDao.findOne(id);
	}

	@Override
	public Collection<Book> getAll() {

		return bookDao.findAll();
	}

	@Override
	public void insert(Book entity) {
		bookDao.insert(entity);

	}

	@Override
	public void update(Book entity) {
		bookDao.update(entity);

	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);

	}

}
