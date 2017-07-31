package tw.com.softleader.sample.country;

import java.sql.Date;

public class CService {
	
	public static void main(String [] args){
	ConvertService cs=new ConvertService();
	java.util.Date date=cs.convert("19870123");
	
	System.out.println(date);
	}
	
}
