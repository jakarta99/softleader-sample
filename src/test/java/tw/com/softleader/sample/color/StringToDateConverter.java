package tw.com.softleader.sample.color;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, LocalDate>{
	
//	private final DateTimeFormatter formatter;
	
//	public StringToDateConverter(String dateFormat) {
//		this.formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//	}

	@Override
	public LocalDate convert(String source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		
		return LocalDate.parse(source, formatter);
//        if (source == null || source.isEmpty()) {
//            return null;
//        }
//        
//        return LocalDate.parse(source, formatter);
	}

}
