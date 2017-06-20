package tw.com.softleader.sample.books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import tw.com.softleader.sample.commons.GenericService;
import tw.com.softleader.sample.drink.Drink;



public class BookService implements GenericService<Book> {
	
	private List<Book> books = new ArrayList<Book>();
	public BookService() {

		Book book1 = new Book();
		book1.setId(1);
		book1.setName("Harry Potter");
		book1.setType("magic");
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setName("juice");
		book2.setType("religious");
		
		Book book3 = new Book();
		book3.setId(3);
		book3.setName("tea");
		book3.setType("mysterious");
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
	}
	@Override
	public Book getOne(int id) {
		if(id==3){
			return null;
		}
		return null;
	}
	@Override
	public List<Book> getAll() {
		return books;
	}
	@Override
	public void insert(Book data) {
	}
	@Override
	public void update(Book data) {
		
	}
	@Override
	public void delete(int id) {
		
	}
	



}
