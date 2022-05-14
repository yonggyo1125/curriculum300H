package day08_10.abstractex;

public class ComputerTest {
	public static void main(String[] args) {
		//Computer c1 = new Computer(); // 클래스를 인스턴스로 생성할 수 없음
		Computer c2 = new DeskTop();
		//Computer c3 = new NoteBook(); // 클래스를 인스턴스로 생성할 수 없음
		Computer c4 = new MyNoteBook();
	}
}
