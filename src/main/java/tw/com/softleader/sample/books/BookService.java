package tw.com.softleader.sample.books;

import java.util.Arrays;

import tw.com.softleader.sample.commons.GenericService;

public class BookService implements GenericService {

	private String[] books = { "Harry Porter", "The Da Vinci Code", "Angels & Demons" };

	@Override
	public String[] getAll() {
		return books;
	}

	@Override
	public String getOne(int id) {
		String book = books[id];
		return book;
	}

	@Override
	public void insert(String data) {
		books = Arrays.copyOf(books, books.length + 1);
		books[books.length - 1] = data;
	}

	@Override
	public void update(String data, int id) {
		books[id] = data;
	}

	@Override
	public void delete(String data) {
		String[] newbooks=new String[books.length-1];
		for (int i = 0; i < books.length; i++) {
			if (!books[i].equals(data)) {
				newbooks[i]=books[i];
			}
		}

	}

}
