package tw.com.softleader.sample.human;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.core.convert.converter.Converter;

public abstract class TimeConverter<R> implements Converter<String, R> {
	
	abstract Function<String, R> convertFunction();
	
	abstract BiFunction<String, DateTimeFormatter, R> convertBiFunction();
	
	protected DateTimeFormatter formatter;
	
	@Override
	public R convert(String source) {
		if (source == null || source.length() == 0) {
			return null;
		}
		if (formatter == null) {
			return this.convertFunction().apply(source);
		} else {
			return this.convertBiFunction().apply(source, formatter);
		}
	}
	
}
