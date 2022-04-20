package day18;

public class MyCalculatorParamTest {
	public static void main(String[] args) {
		calcuPlus((x, y) -> x + y);
	}
	
	public static void calcuPlus(MyCalculator calcu) {
		int result = calcu.plus(10, 20);
		System.out.println(result);
	}
}
