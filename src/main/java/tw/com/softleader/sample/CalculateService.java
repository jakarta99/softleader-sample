package tw.com.softleader.sample;

public class CalculateService {

	public int sum(int a, int b) {
		return a+b;
	}

	// user1
	public int substract(int a, int b) {
		return (a - b);
	}

	// user2
	public int multiply(int a, int b) {
		return (a * b);
	}

	// user3
	public double divide(int a, int b) {
		return a/b;
	}

	// user4 取餘數
	public int remainder(int a, int b) {
		return (a%b);
	}

	
	// user5 平方
	public int square(int a) {
		return (a*a);
	}

	// user6 三次方
	public int power3(int a) {
		return (a*a*a);
	}
}
