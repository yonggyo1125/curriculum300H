package day08_10.abstractex;

public abstract class Computer {  
	public abstract void display(); // 더이상 오류 없음
	public abstract void typing(); // 더이상 오류 없음 
	
	public void turnOn() {
		System.out.println("전원을 켭니다.");
	}
	
	public void turnOff() {
		System.out.println("전원을 끕니다.");
	}
}
