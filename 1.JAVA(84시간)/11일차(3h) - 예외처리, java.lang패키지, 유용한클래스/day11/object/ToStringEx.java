package day11.object;

class Book {
	int bookNumber;
	String bookTitle;
	
	Book(int bookNumber, String bookTitle) {
		this.bookNumber = bookNumber;
		this.bookTitle = bookTitle;
	}
	
	@Override
	public String toString() { // toString() 메서드 재정의
		return bookTitle + "," + bookNumber;
	}
}

public class ToStringEx {
	public static void main(String[] args) {
		Book book1 = new Book(200, "개미");
		
		 // 클래스 정보(클래스 이름, 주소 값)
		System.out.println(book1);
		
		// toString())) 메서드로 인스턴스 정보(클래스 이름, 주소 값)를 보여 줌
		System.out.println(book1.toString());
	}
}
