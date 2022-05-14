package day16;

class Parent {
	void parentMethod() {}
}

public class AnnotationEx1 extends Parent{
	@Override
	void parentmethod() {} // 상위 클래스의 메서드의 이름을 잘못 적음
}
