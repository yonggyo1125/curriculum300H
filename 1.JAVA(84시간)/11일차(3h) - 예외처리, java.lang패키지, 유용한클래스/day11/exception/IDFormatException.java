package day11.exception;

public class IDFormatException extends Exception {
	// 생성자의 매개변수로 예외 상황 메시지를 받음
	public IDFormatException(String message) {
		super(message);
	}
}
