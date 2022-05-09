package day05_07;

public class Student {
	int studentID; 
	String studentName;
	int grade; 
	String address; 
	

	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String name) {
		studentName = name;
	}
	
	// main() 함수 
	public static void main(String[] args) {
		Student studentAhn = new Student(); // Student 클래스 생성
		studentAhn.studentName = "안연수";
		
		System.out.println(studentAhn.studentName);
		System.out.println(studentAhn.getStudentName());
	}
}
