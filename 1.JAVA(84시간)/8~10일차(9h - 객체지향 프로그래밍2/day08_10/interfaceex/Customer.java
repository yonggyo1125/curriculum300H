package day08_10.interfaceex;

// Customer 클래스는 Buy와 Sell 인터페이스를 모두 구현함 
public class Customer extends Shop implements Buy, Sell {

	@Override
	public void sell() {
		System.out.println("판매하기");
	}

	@Override
	public void buy() {
		System.out.println("구매하기");
	}
	
	// 디폴트 메서드 order()를 Customer 클래스에서 재정의함
	@Override
	public void order() {
		System.out.println("고객 판매 주문");
	}	
}
