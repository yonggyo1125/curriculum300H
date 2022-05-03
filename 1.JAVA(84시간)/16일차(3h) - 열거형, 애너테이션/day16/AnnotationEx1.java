package day16;

class Parent {
	void parentMethod() {}
}

public class AnnotationEx1 extends Parent{
	@Override
	void parentmethod() {} // 조상 메서드의 이름을 잘못 적음
}
