package day02_03.loopexample;

public class DoWhileExample {
	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		do {
			// 주어진 조건이 참이 아니더라도 무조건 한 번 수행함
			sum += num;
			num++;
		} while(num <= 10);
		
		System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
	}
}
