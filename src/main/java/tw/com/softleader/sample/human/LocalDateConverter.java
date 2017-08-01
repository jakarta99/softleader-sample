package tw.com.softleader.sample.human;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LocalDateConverter extends TimeConverter<LocalDate>{

	public LocalDateConverter() {
		super();
	}
	
	public LocalDateConverter(DateTimeFormatter formatter) {
		super();
		this.formatter = formatter;
	}
	
	@Override
	Function<String, LocalDate> convertFunction() {
		return LocalDate::parse;
	}

	@Override
	BiFunction<String, DateTimeFormatter, LocalDate> convertBiFunction() {
		return LocalDate::parse;
	}

}
