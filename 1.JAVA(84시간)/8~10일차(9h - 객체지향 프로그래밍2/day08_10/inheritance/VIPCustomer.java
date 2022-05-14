package day08_10.inheritance;

// VIPCustomer 클래스는 Customer 클래스를 상속받음
public class VIPCustomer extends Customer { 
	private int agentID; // VIP 고객 상담원 아이디
	double saleRatio; // 할인율
	
	public VIPCustomer(int customerID, String customerName, int agentId) {
		super(customerID, customerName);
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		
		this.agentID = agentId;
		
		// 하위 클래스를 생설할 때 콘솔 출력문
		//System.out.println("VIPCustomer() 생성자 호출");
	}
	
	public int getAgentID() {
		return agentID;
	}
	
	@Override
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		
		return price - (int)(price * saleRatio); // 할인된 가격을 계산하여 반환
	}
}