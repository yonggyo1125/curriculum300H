package day18;

public class MyCalulatorTest {
	public static void main(String[] args) {
		MyCalculator calcu = (x, y) -> x + y;
		int result = calcu.plus(20, 30);
		
		System.out.println(result);
	}
}
