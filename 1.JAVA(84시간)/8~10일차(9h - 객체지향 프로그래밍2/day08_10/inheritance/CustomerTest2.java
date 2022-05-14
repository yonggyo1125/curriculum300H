package day08_10.inheritance;

public class CustomerTest2 {
	public static void main(String[] args) {
		/**
		VIPCustomer customerKim = new VIPCustomer(); // 하위 클래스 생성
		customerKim.setCustomerID(1020);
		customerKim.setCustomerName("김유신");
		*/
		VIPCustomer customerKim = new VIPCustomer(1020, "김유신", 1000);
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
	}
}
