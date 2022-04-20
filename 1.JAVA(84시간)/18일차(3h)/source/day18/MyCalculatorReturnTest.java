package day18;

public class MyCalculatorReturnTest {
	public static void main(String[] args) {
		MyCalculator calcu1 = calcuPlus();
		int result1 = calcu1.plus(10,20);
		System.out.println(result1);
		
		MyCalculator calcu2 = calcuPlus2();
		int result2 = calcu2.plus(10,20);
		System.out.println(result2);
	}
	
	public static MyCalculator calcuPlus() {
		MyCalculator calcu = (x, y) -> x + y;
		
		return calcu;
	}

	public static MyCalculator calcuPlus2() {		
		return (x, y) -> x + y;
	}
}
