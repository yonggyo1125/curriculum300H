package day02_03.loopexample;

public class WhileExample1 {
	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		while(num <= 10) { // num값이 10보다 작거나 같을 동인
			sum += num; // 합계를 뜻하는 sum에 num을 더하고 
			num++; // num에 1씩 더해 나감 
		}
		System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
	}
}
