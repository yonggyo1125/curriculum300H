package day08_10.polymorphism;

public class Customer {
	protected int customerID;
	protected String customerName;
	protected String customerGrade;
	int bonusPoint;
	double bonusRatio;
	
	public Customer()
	{
		//  고객 등급과 보너스 포인트 적립률 지정 함수 호출
		initCustomer(); 
	}

	public Customer(int customerID, String customerName){
		this.customerID = customerID;
		this.customerName = customerName;
		
		// 고객 등급과 보너스 포인트 적립률 지정 함수 호출
		initCustomer();
	}
	
	// 생성자에서만 호출하는 메서드이므로 private으로 선언
	// 멤버 변수의 초기화 부분
	private void initCustomer()
	{
		customerGrade = "SILVER";
		bonusRatio = 0.01;	
	}
	
	public int calcPrice(int price){
		bonusPoint += price * bonusRatio;
		return price;
	}
	
	public String showCustomerInfo(){
		return customerName + " 님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "점입니다.";  
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}
}
