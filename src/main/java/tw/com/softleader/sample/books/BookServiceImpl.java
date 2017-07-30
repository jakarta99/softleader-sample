package tw.com.softleader.sample.books;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private String defaultType="yellow"; 
	
	public BookServiceImpl(){
		
	}
	
	public BookServiceImpl(String type){
		this.defaultType=type;
	}
	

	@Override
	public Book getOne(Long id) {
		BookDao bookDao = new BookDao();
		return bookDao.findOne(id);
	}

	@Override
	public Collection<Book> getAll() {
		System.out.println(defaultType);

		BookDao bookDao = new BookDao();
		return bookDao.findAll();
	}

	@Override
	public void insert(Book entity) {
		BookDao bookDao = new BookDao();
		bookDao.insert(entity);
	}

	@Override
	public void update(Book entity) {
		BookDao bookDao = new BookDao();
		bookDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		BookDao bookDao = new BookDao();
		bookDao.delete(id);
		
	}
	
	
}

