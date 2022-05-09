package day05_07.constructor;

public class PersonTest2 {
	public static void main(String[] args) {
		Person personKim = new Person();
		personKim.name = "김유신";
		personKim.weight = 85.5f; 
		personKim.height = 180.0f;
		
		Person personLee = new Person("이순신", 175, 75);
	}
}
