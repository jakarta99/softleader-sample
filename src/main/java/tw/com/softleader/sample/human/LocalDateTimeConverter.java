package tw.com.softleader.sample.human;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LocalDateTimeConverter extends TimeConverter<LocalDateTime> {
	
	public LocalDateTimeConverter() {
		super();
	}
	
	public LocalDateTimeConverter(DateTimeFormatter formatter) {
		super();
		this.formatter = formatter;
	}
	
	@Override
	Function<String, LocalDateTime> convertFunction() {
		return LocalDateTime::parse;
	}
	
	@Override
	BiFunction<String, DateTimeFormatter, LocalDateTime> convertBiFunction() {
		return LocalDateTime::parse;
	}
	
}
