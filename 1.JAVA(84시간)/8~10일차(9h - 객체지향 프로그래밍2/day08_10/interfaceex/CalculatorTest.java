package day08_10.interfaceex;

public class CalculatorTest {
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 5;
		
		CompleteCalc calc = new CompleteCalc();
		System.out.println(calc.add(num1, num2));
		System.out.println(calc.subtract(num1, num2));
		System.out.println(calc.times(num1, num2));
		System.out.println(calc.divide(num1, num2));
		calc.showInfo();
		calc.description(); // 디폴트 메서드 호출
		
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(Calc.total(arr));
	}
}
