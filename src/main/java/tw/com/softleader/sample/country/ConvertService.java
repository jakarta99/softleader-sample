package tw.com.softleader.sample.country;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

public class ConvertService implements Converter<String,java.util.Date>{

	@Override
	public java.util.Date convert(String Input) {
		String input=Input.trim();
		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
		java.util.Date date=null;
		try {
			date = (java.util.Date) format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
