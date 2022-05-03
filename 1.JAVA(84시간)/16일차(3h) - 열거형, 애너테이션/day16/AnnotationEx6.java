package day16;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 실행시에사용가능하도록 지정
@interface TestInfo {
	int count() default 1;
	String testedBy();
	String[] testTools() default "JUnit";
	TestType testType() default TestType.FIRST;
	DateTime testDate();
}

@Retention(RetentionPolicy.RUNTIME) // 실행 시에 사용가능하도록 지정
@interface DateTime {
	String yymmdd();
	String hhmmss();
}

enum TestType { FIRST, FINAL }

@Deprecated
@TestInfo(testedBy="aaa", testDate=@DateTime(yymmdd="160101", hhmmss="235959"))
public class AnnotationEx6 {
	public static void main(String[] args) {
		// AnnotationEx6의 Class 객체를 얻는다.
		Class<AnnotationEx6> cls = AnnotationEx6.class;
		
		TestInfo anno = (TestInfo)cls.getAnnotation(TestInfo.class);
		System.out.println("anno.testedBy()=" + anno.testedBy());
		System.out.println("anno.testDate().yymmdd()" + anno.testDate().yymmdd());
		System.out.println("anno.testDate().hhmmss()=" + anno.testDate().hhmmss());
		
		for(String str : anno.testTools()) {
			System.out.println("testTools=" + str);
		}
		
		System.out.println();
		
		// AnnotationEx6에 적용된 모든 애너테이션을 가져온다.
		Annotation[] annoArr = cls.getAnnotations();
		
		for (Annotation a : annoArr) {
			System.out.println(a);
		}
	}
}
