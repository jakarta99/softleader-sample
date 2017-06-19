package tw.com.softleader.sample.books;

import tw.com.softleader.sample.commons.GenericService;

public class BookService implements GenericService {

	@Override
	public String[] getAll() {
		String[] books = { "Harry Porter", "The Da Vinci Code", "Angels & Demons" };
		return books;
	}

}
