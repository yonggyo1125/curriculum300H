package day08_10.innerclass;

class Outer {
	int outNum = 100;
	static int sNum = 200;
	
	Runnable getRunnable(int i) {
		int num = 100; // 지역변수
		
		class MyRunnable implements Runnable { // 지역 내부 클래스
			int localNum = 10; // 지역 내부 클래스의 인스턴스 변수
			
			@Override
			public void run() {
				//num = 200; 지역변수는 상수로 바뀌므로 값을 변경할 수 없어 오류 발생
				//i = 100; // 매개변수도 지역변수처럼 상수로 바뀌므로 값을 변경할 수 없어 오류 발생 
				
				System.out.println("i = " + i);
				System.out.println("num = " + num);
				System.out.println("localNum = " + localNum);
				System.out.println("outNum = " + outNum + "(외부 클래스 인스턴스 변수)");
				System.out.println("Outer.sNum = " + Outer.sNum + "(외부 클래스 정적 변수)");
			}
		}
		
		return new MyRunnable();
	}
}

public class LocalInnerTest {
	public static void main(String[] args) {
		Outer out = new Outer();
		Runnable runner = out.getRunnable(10); // 메서드 호출
		runner.run();
	}
}