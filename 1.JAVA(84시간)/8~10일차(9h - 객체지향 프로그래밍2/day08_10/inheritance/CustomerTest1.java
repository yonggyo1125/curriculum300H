package day08_10.inheritance;

public class CustomerTest1 {
	public static void main(String[] args) {
		//Customer customerLee = new Customer();
		// CustomerID와 customerName은 protected 변수이므로 set() 메서드 호출
		//customerLee.setCustomerID(10010);
		//customerLee.setCustomerName("이순신");
		Customer customerLee = new Customer(10010, "이순신");
		customerLee.bonusPoint = 1000;
		System.out.println(customerLee.showCustomerInfo());
		
		//VIPCustomer customerKim = new VIPCustomer();
		// CustomerID와 customerName은 protected 변수이므로 set() 메서드 호출
		//customerKim.setCustomerID(10020);
		//customerKim.setCustomerName("김유신");
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신", 1000);
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
	}
}
