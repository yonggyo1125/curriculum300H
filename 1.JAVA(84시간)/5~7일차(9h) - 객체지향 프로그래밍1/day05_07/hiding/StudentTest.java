package day05_07.hiding;

public class StudentTest {
	public static void main(String[] args) {
		Student studentLee = new Student();
		//studentLee.studentName = "이상원"; // 오류발생
		
		// setStudentName() 메서드 활용해 private 변수에 접근 가능
		studentLee.setStudentName("이상원");
		
		System.out.println(studentLee.getStudentName());
	}
}
