package day11.exception;

public class ArrayExceptionHandling {
	public static void main(String[] args) {
		int[] arr = new int[5];
		
		try {
			// 예외가 발생할 수 있으므로 try 블록에 작성 
			for (int i = 0; i <= 5; i++) {
				arr[i] = i;
				System.out.println(arr[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// 예외가 발생하면 catch 블록 수행 
			System.out.println(e);
			System.out.println("예외처리 부분");
		}
	}
}
