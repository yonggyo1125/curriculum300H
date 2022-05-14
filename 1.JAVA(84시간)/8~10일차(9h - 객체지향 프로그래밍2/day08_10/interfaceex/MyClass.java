package day08_10.interfaceex;

public class MyClass implements MyInterface {

	// X 인터페이스에서 상속받은 x() 메서드 구현 
	@Override
	public void x() {
		System.out.println("x()");
	}

	// Y 인터페이스에서 상속받은 y() 메서드 구현
	@Override
	public void y() {
		System.out.println("y()");
	}

	@Override
	public void myMethod() {
		System.out.println("myMethod()");
	}
}
