package day08_10.innerclass;

class OutClass { // 외부 클래스
	private int num = 10; // 외부 클래스 private 변수
	private static int sNum = 20; // 외부 클래스 정적 변수
	
	private InClass inClass; // 내부 클래스 자료형 변수를 먼저 선언
	
	static class InStaticClass { // 정적 내부 클래스 
		int inNum = 100; // 정적 내부 클래스의 인스턴스 변수
		static int sInNum = 200; // 정적 내부 클래스의 정적 변수
		
		// 정적 내부 클래스의 일반 메서드
		void inTest() {
			//num += 10; // 외부 클래스의 인스턴스 변수는 사용할 수 없다.
			System.out.println("InStaticClass inNum = " + inNum + "(내부 클래스의 인스턴스 변수 사용)");
			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 정적 변수 사용)");
			System.out.println("OutClass sNum = " + inNum + "(외부 클래스의 정적 변수 사용)");
		}
		
		// 정적 내부 클래스의 정적 메서드
		static void sTest() {
			// 외부 클래스와 내부 클래스의 인스턴스 변수는 사용할 수 없다.
			//num += 10;
			//inNum += 10
			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 정적 변수 사용)");
			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 정적 변수 사용)");
			
		}
	}
	
	
	 // 외부 클래스 디폴트 생성자, 외부 클래스가
	// 생성된 후 내부 클래스 생성 가능
	public OutClass() {
		inClass = new InClass();
	}
	
	class InClass { // 인스턴스 내부 클래스 
		int inNum = 100;  // 내부 클래스의 인스턴스 변수
		// JDK15까지는 인스턴스 내부 클래스에 정적변수 선언이 불가능, JDK16부터는 가능
		//static int sInNum = 200;
		
		void inTest() {
			System.out.println("OutClass num = " + num + "(외부 클래스의 인스턴스 변수)");
			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 정적 변수)");
		}
		
		//JDK15까지는 인스턴스 내부 클래스에 정적메서드 선언이 불가능, JDK16부터는 가능
		//static void sTest() {
		//}
	}
	
	public void usingClass() {
		inClass.inTest();
	}
}

public class InnerTest {
	public static void main(String[] args) {
		OutClass outClass = new OutClass();
		System.out.println("외부 클래스 이용하여 내부 클래스 기능 호출");
		outClass.usingClass(); // 내부 클래스 기능 호출
		
		OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
		System.out.println("정적 내부 클래스 일반 메서드 호출");
		sInClass.inTest();
		System.out.println();
		System.out.println("정적 내부 클래스의 정적 메서드 호출");
		OutClass.InStaticClass.sTest();
	}
}
