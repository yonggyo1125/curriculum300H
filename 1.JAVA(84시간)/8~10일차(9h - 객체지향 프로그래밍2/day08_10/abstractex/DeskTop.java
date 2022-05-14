package day08_10.abstractex;

public class DeskTop extends Computer {

	@Override
	public void display() {
		// 추상 메서드의 몸체 코드 작성
		System.out.println("DeskTop display()"); 
		
	}

	@Override
	public void typing() {
		// 추상 메서드의 몸체 코드 작성
		System.out.println("DeskTop typing()");
		
	} 
}
