package day16;

import java.lang.annotation.*;

@interface ToDos {
	ToDo[] value();
}

@Repeatable(ToDos.class)
 @interface ToDo {
	String value();
}

@ToDo("자바 공부")
@ToDo("자바스크립트 공부")
@ToDo("데이터베이스 공부")
public class AnnotationEx5 {
	
}
