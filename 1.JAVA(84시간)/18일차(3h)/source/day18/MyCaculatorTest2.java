package day18;

public class MyCaculatorTest2 {
	public static void main(String[] args) {
		
		int num3 = 10;
		MyCalculator calcu = new MyCalculator() {
			@Override
			public int plus(int num1, int num2) {
				//num3 = 20; 익명(지역) 내부 클래스의 메서드에서 사용되는 지역 변수는 상수화 된다
				int result = num1 + num2 + num3;
				return result;
			}
		};
		
		
		int result = calcu.plus(20, 30);
		System.out.println(result);
	}
}
