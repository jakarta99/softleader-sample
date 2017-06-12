package tw.com.softleader.sample;

public class SampleApp {

	public static void main(String[] args) {

		CalculateService calculateService = new CalculateService();

		System.out.println(calculateService.sum(1, 3));
		System.out.println(calculateService.substract(4, 5));

	}
}
