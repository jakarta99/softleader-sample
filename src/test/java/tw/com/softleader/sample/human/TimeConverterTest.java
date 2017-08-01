package tw.com.softleader.sample.human;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public class TimeConverterTest {

	@Test
	public void tryTimeString() {
		final LocalDateTime presentDateTime = LocalDateTime.now();
		final String presentDateTimeString = presentDateTime.toString();
		
		assertEquals(presentDateTime, new LocalDateTimeConverter().convert(presentDateTimeString));
		
		final LocalDate presentDate = LocalDate.now();
		final String presentDateString = presentDate.toString();
		
		assertEquals(presentDate, new LocalDateConverter().convert(presentDateString));
	}
	
}
