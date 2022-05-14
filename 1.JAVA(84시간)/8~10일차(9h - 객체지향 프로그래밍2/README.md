# 상속과 다형성

## 상속이란?
- 객체 지향 프로그래밍의 중요한 특징 중 하나가 **상속**(inheritance)입니다. 
- 상속은 우리가 일반적으로 알 듯 무엇인가를 물려받는다는 의미 입니다. 일례로 부모가 자식에게 물려주는 재산을 상속이라고 하고, 상속받은 재산은 자신의 것으로 사용할 수 있습니다.
- 객체 지향 프로그램에서도 마찬가지로 B클래스가 A클래스를 상속받으면 B클래스는 A클래스를 상속받으면 B클래스는 A클래스의 메서드를 사용할 수 있습니다.
- 객체 지향 프로그램은 유지보수하기 편하고 프로그램을 수정하거나 새로운 내용을 추가하는 것이 유연한데, 그 기반이 되는 기술이 **상속**입니다. 

### 클래스의 상속
- B 클래스가 A 클래스에서 상속 받는다고 할 때 다음과 같은 그림으로 나타낼 수 있습니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D1.png'>

- 클래스 간 상속을 표현할 때는 위 그림에 표현한 것 처럼 상속받는 클래스에서 상속하는 클래스로 화살표가 갑니다. 
- 부모 클래스(parent class)를 '상위클래스 또는 조상클래스', 자식 클래스를 '하위 클래스' 등으로 부르기도 합니다.

### 클래스 상속 문법
- 자바 문법으로 상속을 구현할 때는 **extends 예약어**를 사용합니다.
- 이때 사용하는 extends 예약어는 **연장, 확장하가**의 의미입니다. 즉 A가 가지고 있는 속성이나 기능을 추가로 확장하여 B클래스를 구현한다는 끗
- 그러면 일반적인 클래스 A에서 더 구체적인 클래스 B가 구현됩니다.
- 하기 코드는 **B클래스가 A 클래스를 상속받는다**라고 말합니다.
```
class B extends A {

}
```

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D2.png'>

- 포유류는 사람보다 일반적인 개념입니다.
- 사람은 포유류의 특징과 기능을 기본으로 더 많거나 다른 특징과 기능을 가지고 있습니다. 
- 상속관계에서는 **상위 클래스가 하위클래스보다 일반적인 개념**이고, **하위 클래스는 상위 클래스보다 구체적인 클래스**가 됩니다.

### 상속을 사용하여 고객 관리 프로그램 구현하기

#### day08_10/inheritance/Customer.java
```
package day08_10.inheritance;

public class Customer {
	
	// 멤버 변수
	private int CustomerID; // 고객 아이디
	private String customerName; // 고객 이름
	private String customerGrade; // 고객 등급
	int bonusPoint; // 보너스 포인트
	double bonusRatio; // 적립비율
	
	// 디폴트 생성자
	public Customer() {
		customerGrade = "SILVER"; // 기본 등급
		bonusRatio = 0.01; // 보너스 포인트 기본 적립 비율
	}
	
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio; // 보너스 포인트 계산
		return price;
	}
	
	public String showCustomerInfo() {
		return customerName + " 님의 등급은 " + customerGrade + "이며, 보너스 포인트는" + bonusPoint + "입니다."; 
	}
}
```
- 예제에서 사용한 멤버변수를 살펴보면 다음과 같습니다.

|멤버변수|설명|
|customerID|고객 아이디|
|customerName|고객 이름|
|customerGrade|고객 등급<br>기본 생성자에서 지정되는 기본 등급은 SILVER입니다.|
|bonusPoint|고객의 보너스 포인트<br>- 고객이 제품을 구매할 경우 누적되는 보너스 포인트입니다.|
|bonusRatio|보너스 포인트 적립 비율<br>- 고객이 제품을 구매할 때 구매 금액의 일정 비율이 보너스 포인트로 적립됩니다.<br>- 기본 생성자에서 지정되는 적립 비율은 1%입니다. 즉, 10,000원짜리를 사면 100원이 적립됩니다.|

- 모든 멤버 변수를 반드시 private으로 선언할 필요는 없습니다. 필요에 따라서 멤버 변수나 메서드를 외부에 노출하지 않을 목적일 때 private으로 선언합니다. 

|메서드|설명|
|Customer()|기본 생성자입니다. 고객 한 명이 새로 생성되면 CustomerGrade는 SILVER이고, bonusRatio는 1%로 지정합니다.|
|calcPrice(int price)|제품에 대해 지불해야 하는 금액을 계산하여 반환합니다. 할인되지 않는 경우 가격을 그대로 반환합니다. 그리고 가격에 대해 보너스 포인트 비율을 적용하여 보너스 포인트를 적립합니다.|
|showCustomerInfo()|고객 정보를 출력합니다. 고객 이름과 등급, 현재 적립된 포인트를 보여줍니다.|

#### 새로운 고객 등급이 필요한 경우
```
예제 시나리오

고객이 점점 늘어나고 판매도 많아지고 보니 단골 고객이 생겼습니다. 
단골 고객은 회사 매출에 많은 기여를 하는 우수 고객입니다. 
이 우수 고객에게 좋은 혜택을 주고 싶습니다. 
우수 고객은 VIP이고, 다음과 같은 혜택을 제공합니다.

- 제품을 살 때는 항상 10% 할인해 줍니다.
- 보너스 포인트를 5% 적립해 줍니다.
- 담당 전문 상담원을 배정해 줍니다.
```
- Customer 클래스에 일반 곡개의 속성과 기능이 이미 구현되어 있기 때문에 VIPCustomer 클래스는 Customer 클래스를 상속받고 VIP고객에게 필요한 추가 속성과 기능을 구현하는 것 입니다.

#### day08_10/inheritance/VIPCustomer.java
```
package day08_10.inheritance;

// VIPCustomer 클래스는 Customer 클래스를 상속받음
public class VIPCustomer extends Customer { 
	private int agentID; // VIP 고객 상담원 아이디
	double saleRatio; // 할인율
	
	public VIPCustomer() {
		customerGrade = "VIP"; // 상위 클래스가 private변수 이므로 오류 발생 
		bonusRatio = 0.05;
		saleRatio = 0.1;
	}
	
	public int getAgentID() {
		return agentID;
	}
}
```
- 간단하게 상속을 통해서 Customer의 멤버변수와 메서드를 공유하는 VIPCustomer  클래스를 작성하였습니다.
- Customer 클래스에 이미 선언되어 있는 customerID, customerName, customerGrade, bonusPoint, bonusRatio 멤버 변수와 calcPrice(), showCustomerInfo()메서드는 상속을 받아서 사용할 것이기 때문에 구현하지 않았습니다.
- 그러나 customerGrade변수에 오류가 발생합니다. 상위 클래스에서 customerGrade는 private 변수이고 외부 클래스에서는 이 변수를 사용할 수 없습니다.

#### 상위 클래스 변수를 사용하기 위한 protected 예약어
- 상위 클래스에 선언한 customerGrade가 private 변수이기 때문에 오류가 발생합니다.
- 상위 클래스에 작성한 변수나 메서드 중 외부 클래스에서 사용할 수 없지만 하위 클래스에서 사용할 수 있도록 지정하는 예약어가 protected 입니다. protected로 변경을 하면 하위 클래스에서는 접근할 수 있게 됩니다.
- 즉, protected는 상속된 하위 클래스를 제외한 나머지 외부 클래스에서는 private과 동일한 역할을 합니다.

#### day08_10/inheritance/Customer.java
```
package day08_10.inheritance;

public class Customer {
	
	// 멤버 변수
	protected int CustomerID; // 고객 아이디
	protected String customerName; // 고객 이름
	protected String customerGrade; // 고객 등급
	int bonusPoint; // 보너스 포인트
	double bonusRatio; // 적립비율
	
	// 디폴트 생성자
	public Customer() {
		customerGrade = "SILVER"; // 기본 등급
		bonusRatio = 0.01; // 보너스 포인트 기본 적립 비율
	}
	
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio; // 보너스 포인트 계산
		return price;
	}
	
	public String showCustomerInfo() {
		return customerName + " 님의 등급은 " + customerGrade + "이며, 보너스 포인트는" + bonusPoint + "입니다."; 
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
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
```
- Customer클래스에 있는 private 변수를 다른 하위 클래스에서도 사용할 수 있도록 모두 protected로 변경하였습니다.
- protected로 선언한 customerID, customerName, customerGrade 변수를 사용하기 위해 get(), set()메서드를 추가 하였습니다.
- protected 예약어로 선언한 변수는 외부 클래스 private 변수처럼 get() 메서드를 사용해 값을 가져올 수 있고, set() 메서드를 사용해 값을 지정할 수 있습니다.
- Customer클래스를 상속받은 VIPCustomer 클래스는 protected로 선언한 변수를 상속받게 되고, 나머지 public 메서드도 상속받아 사용할 수 있습니다. 상기 코드와 같이 protected로 선언하면 VIPCustomer 부분의 오류는 사라집니다.

#### day08_10/inheritance/CustomerTest1.java
```
package day08_10.inheritance;

public class CustomerTest1 {
	public static void main(String[] args) {
		Customer customerLee = new Customer();
		// CustomerID와 customerName은 protected 변수이므로 set() 메서드 호출
		customerLee.setCustomerID(10010);
		customerLee.setCustomerName("이순신");
		customerLee.bonusPoint = 1000;
		System.out.println(customerLee.showCustomerInfo());
		
		VIPCustomer customerKim = new VIPCustomer();
		// CustomerID와 customerName은 protected 변수이므로 set() 메서드 호출
		customerKim.setCustomerID(10020);
		customerKim.setCustomerName("김유신");
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
	}
}

실행결과

이순신 님의 등급은 SILVER이며, 보너스 포인트는1000입니다.
김유신 님의 등급은 VIP이며, 보너스 포인트는10000입니다.
```

## 상속에서 클래스 생성과 형 변환
- 하위 클래스가 생성될 때는 상위 클래스의 생성자가 먼저 호출됩니다.
- 상속관계에서 클래스의 생성과정을 살펴보면 하위클래스가 상위클래스의 변수와 메서드를 사용할 수 있는 이유와 하위클래스가 상위클래스의 자료형으로 형 변환을 할 수 있는 이유를 이해할 수 있습니다.

### 하위 클래스가 생성되는 과정 
- 상속을 받은 하위 클래스는 상위클래스의 변수와 메서드를 사용할 수 있습니다. 
- 즉, CustomerTest예제를 살펴보면 VIPCustomer 클래스로 선언한 customerKim 인스턴스는 상속받은 상위 클래스의 변수를 자기 것 처럼 사용할 수 있습니다.
- 변수를 사용할 수 있다는 것은 그 변수를 저장하고 있는 메모리가 존재한다는 뜻입니다.
- 그런데 VIPCustomer 클래스의 코드를 보면 해당 변수가 존재하지 않습니다. 단순히 Customer 클래를 상속받았을 뿐 입니다.
- 여기에서 상속된 하위 클래스가 생성되는 과정을 다시 생각해 볼 필요가 있습니다.
- 테스트를 하기 위해 Customer와 VIPCustomer 클래스 생성자에 출력문을 추가하겠습니다.

#### day08_10/inheritance/Customer.java
```
package day08_10.inheritance;

public class Customer {
	
	// 멤버 변수
	protected int CustomerID; // 고객 아이디
	protected String customerName; // 고객 이름
	protected String customerGrade; // 고객 등급
	int bonusPoint; // 보너스 포인트
	double bonusRatio; // 적립비율
	
	// 디폴트 생성자
	public Customer() {
		customerGrade = "SILVER"; // 기본 등급
		bonusRatio = 0.01; // 보너스 포인트 기본 적립 비율
		
		// 상위 클래스 생성할 때 콘솔 출력문
		System.out.println("Customer() 생성자 호출"); 
	}
	
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio; // 보너스 포인트 계산
		return price;
	}
	
	public String showCustomerInfo() {
		return customerName + " 님의 등급은 " + customerGrade + "이며, 보너스 포인트는" + bonusPoint + "입니다."; 
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
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
```

#### day08_10/inheritance/VIPCustomer.java
```
package day08_10.inheritance;

// VIPCustomer 클래스는 Customer 클래스를 상속받음
public class VIPCustomer extends Customer { 
	private int agentID; // VIP 고객 상담원 아이디
	double saleRatio; // 할인율
	
	public VIPCustomer() {
		customerGrade = "VIP"; // 상위 클래스가 private변수 이므로 오류 발생 
		bonusRatio = 0.05;
		saleRatio = 0.1;
		
		// 하위 클래스를 생설할 때 콘솔 출력문
		System.out.println("VIPCustomer() 생성자 호출");
	}
	
	public int getAgentID() {
		return agentID;
	}
}
```

#### day08_10/inheritance/CustomerTest2.java
```
package day08_10.inheritance;

public class CustomerTest2 {
	public static void main(String[] args) {
		VIPCustomer customerKim = new VIPCustomer(); // 하위 클래스 생성
		customerKim.setCustomerID(1020);
		customerKim.setCustomerName("김유신");
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
	}
}

실행결과
Customer() 생성자 호출
VIPCustomer() 생성자 호출
김유신 님의 등급은 VIP이며, 보너스 포인트는10000입니다.
```
- 상위클래의 Customer() 생성자가 먼저 호출되고 그 다음에 VIPCustomer()가 호출되는 것을 알 수 있습니다.
- 상위 클래스를 상속받은 하위 클래스가 생성될 때는 반드시 상위 클래스의 생성자가 먼저 호출됩니다. 그리고 상위클래스의 생성자가 호출될 때 상위 클래스의 멤버 변수가 메모리에 생성되는 것입니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D3.png'>

- **상위 클래스의 변수가 메모리에서 먼저 생성**이 되기 때문에 하위 클래스에서도 이 값들을 모두 사용할 수 있습니다.
- private 변수도 동일하게 상위클래스에서 생성이 되지만, 단지 하위 클래스에서 접근할 수 없다는 점을 제외하고는 동일합니다.


### 부모를 부르는 예약어 super

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D4.png'>

- super예약어는 하위클래스에서 상위 클래스로 접근할 때 사용합니다.
- 하위 클래스는 **상위클래스의 주소**, 즉 참조 값을 알고 있습니다. 이 참조 값을 가지고 있는 예약어가 바로 **super**입니다.
- this가 자기 자신의 참조 값을 가지고 있다는 것과 같다고 생각하면 됩니다.
- 또한 **super**는 **상위 클래스의 생성자를 호출**하는 데도 사용합니다.


#### 상위 클래스 생성자 호출하기
- CustomerTest2.java의 예제에서 VIPCustomer만 생성 하였는데, Customer 상위 클래스도 생성된 것을 알 수 있습니다.
- 하위 클래스 생성자만 호출했는데 상위 클래스의 생성자가 호출되는 이유는 **하위 클래스 생성자에서 ** **super**()**를 자동으로 호출하기 때문입니다.** 
- **super()를 호출하면 상위 클래스의 디폴트 생성자가 호출됩니다.**

```
public VIPCustomer() 
	super();  // 컴파일러가 자동으로 초가하는 코드(상위클래스의 Customer()가 호출됨)
	CustomerGrade = "VIP";
	bonusRatio = 0.05;
	saleRatio = 0.1;
	
	System.out.println("VIPCustomer() 생성자 호출");
}
```

#### super 예약어로 매개변수가 있는 생성자 호출하기
- Customer 클래스를 생성할 때 고객 ID와 이름을 반드시 지정해야 한다고 합니다. 이런 경우에 set() 메서드로 값을 지정하는 것이 아니고, 새로운 생성자를 만들어서 매개변수로 값을 전달받을 수도 있습니다.
- 즉, 디폴트 생성자가 아닌 매개변수가 있는 생성자를 직접 구현해야 합니다.
- 다음과 같이 Customer 클래스에 새로운 생성자를 추가하고, 기존의 디폴트 생성자는 삭제하거나 주석 처리해 보겠씁니다.

#### day08_10/inheritance/Customer.java
```
...
// 디폴트 생성자
	/**
	public Customer() {
		customerGrade = "SILVER"; // 기본 등급
		bonusRatio = 0.01; // 보너스 포인트 기본 적립 비율
		
		// 상위 클래스 생성할 때 콘솔 출력문
		System.out.println("Customer() 생성자 호출"); 
	}
	*/
	
	public Customer(int customerID, String customerName) {
		this.customerID = customerID;
		this.customerName = customerName;
		customerGrade = "SILVER";
		bonusRatio = 0.01;
		System.out.println("Customer(int, String) 생성자 호출");
	}
...
```
- 그런데 이렇게 Customer 클래스의 디폴트 생성자를 없애고 새로운 생성자를 작성하면, Customer 클래스를 상속받은 VIPCustomer 클래스에서 오류가 발생합니다.
<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D5.png'>

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D6.png'>

- 이 오류 메세지는 묵시적으로 호출될 디폴트 생성자 Customer()가 정의되지 않았기 때문에, 반드시 명시적으로 다른 생성자를 호출해야 한다는 뜻 입니다.

> 하위 클래스가 생성될 때는 상위 클래스의 디폴트 생성자를 호출하는 super()가 자동으로 생성됩니다.
```
public Customer(int customerID, String customerName) {
	super();  
	-> 컴파일시에 super()가 자동생성되고 상위클래스의 디폴트 생상저 Customer()가 호출되는데 
	-> Customer 클래스에는 디폴트 생성자 Customer()가 없으므로 오류가 발생합니다.
	...
}
```

- Customer 클래스를 새로 생성할 때 고객 ID와 고객 이름을 반드시 지정하여 생성하기로 했으니 VIPCustomer 클래스를 생성할 때도 이 값이 필요합니다.
- 그리고 VIP 고객만을 위한 상담원 ID도 지정합니다.
- 기존 VIPCustomer 클래스의 디폴트 생성자도 지우거나 주석처리한 후 필요한 매개변수를 포함하는 생성자를 새로 작성합니다.

#### day08_10/inheritance/VIPCustomer.java
```
...
public VIPCustomer(int customerID, String customerName, int agentId) {
	super(customerID, customerName);
	customerGrade = "VIP";
	bonusRatio = 0.05;
	saleRatio = 0.1;
		
	this.agentID = agentID;
		
	// 하위 클래스를 생설할 때 콘솔 출력문
	System.out.println("VIPCustomer() 생성자 호출");
}
...
```
- 새로운 생성자는 고객 ID, 고객 이름, 상담원 ID를 매개변수로 받습니다. 
- super 예약어는 상위클래스의 생성자를 호출하는 역할을 하며, 3행의 super(customerID, customerName); 문장으로 상위 클래스 생성자를 호출합니다.
- super()를 통해 Customer(int customerID, String customerName) 상위 클래스 생성자를 호출하고 코드 순서대로 멤버 변수가 초기화 됩니다.
- 상위 클래스 생성자 호출이 끝나면 VIPCustomer 하위 클래스 생성자 내부 코드 수행이 마무리됩니다.

#### day08_10/inheritance/CustomerTest2.java
```
package day08_10.inheritance;

public class CustomerTest2 {
	public static void main(String[] args) {
		VIPCustomer customerKim = new VIPCustomer(1020, "김유신", 1000);
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
	}
}


실행결과

Customer(int, String) 생성자 호출
VIPCustomer() 생성자 호출
김유신 님의 등급은 VIP이며, 보너스 포인트는10000입니다
```
- VIP 등급인 김유신 고객을 생성할 때는 상위클래스 생성자를 먼저 호출한 후 하위 클래스 생성자 코드 수행이 정상적으로 마무리 되는 것을 알 수 있습니다.


#### 상위 클래스의 멤버 변수나 메서드를 참조하는 super
- 상위 클래스에 선언된 멤버 변수나 메서드를 하위 클래스에서 참조할 때도 super를 사용합니다.
- this를 사용하여 자신의 멤버에 접근했던 것과 비슷합니다.
- 예를 들어 VIPCustomer 클래스의 showVIPInfo() 메서드에 상위 클래스의 showCustomerInfo() 메서드를 참조해 담당 상담원 아이디를 추가로 출력하고자 할때 다음과 같이 구현할 수 있습니다.
```
public String showVIPInfo() {
	return super.showCustomerInfo() + "담당 상담원 아이디는 " + agentID + "입니다.";
}
```

- super 예약어는 상위 클래스의 참조 값을 가지고 있으므로 위 코드 처럼 사용하면 고객 정보를 출력하는 showCustomerInfo() 메서드를 새로 구현하지 않고 상위 클래스의 구현 내용을 활용할 수 있습니다.
- 물론 위 코드의 showVIPInfo() 메서드에서는 굳이 show.showCustomerInfo()라고 호출하지 않고 그냥 showCustomerInfo()라고 호출해도 됩니다.
- 메서드 오버라이딩에서 자세하게 설명을 하겠지만 하위클래스가 상위클래스와 동일한 이름의 메서드를 구현하는 경우도 있습니다. 이러한 경우 하위 클래스에서 동일한 이름의 상위 클래스 메서드를 가리킬 때 super.showCustomerInfo()라고 써야 합니다.

### 상위 클래스로 묵시적 클래스 형 변환
- 상속을 공부하면서 이해해야 하는 중요한 관계가 클래스 간의 형 변환입니다.
- Customer와 VIPCustomer의 관계를 생각해보면, 개념 면에서 보면 상위 클래스인 Customer가 VIPCustomer보다 일반적인 개념이고, 기능면에서 보면 VIPCustomer가 Customer보다 기능 이 더 많습니다. 왜냐하면 상속받은 클래스는 상위 클래스의 기능을 모두 사용할 수 있고 추가로 많은 기능을 구현하기 때문입니다.

- 따라서 VIPCustomer는 VIPCustomer형 이면서 동시에 Customer 형이기도 합니다.
- 즉, VIPCustomer 클래스로 인스턴스를 생성할 때 이 인스턴스의 자료형을 Customer형으로 클래스 형 변환하여 선언할 수 있습니다. 왜냐하면 VIPCustomer 클래스는 Customer 클래스를 상속받았기 때문입니다.

> 클래스형과 클래스의 자료형, 인스턴스형과 인스턴스의 자료형은 모두 비슷한 의미로 사용하는 용어입니다.
> 이러한 클래스 형 변환을 업캐스팅(upcasting)이라고도 합니다.

```
Customer vc = new VIPCustomer();

Customer - 선언된 클래스형(상위 클래스형)
VIPCustomer - 생성된 인스턴스의 클래스형(하위 클래스 형)
```

- 반대로 Customer로 인스턴스를 생성할 때 VIPCustomer형으로 선언할 수는 없습니다. 상위 클래스인 Customer가 VIPCustomer 클래스의 기능을 다 가지고 있는 것은 아니기 때문입니다. 요약하면 **모든 하위 클래스는 상위 클래스 자료형으로 형변환 될 수 있지만 그 역은 성립하지 않습니다.**


#### 형 변환된 vc가 가리키는 것 

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D7.png'>

- Customer vc = new VIPCustomer(); 문장이 실행되면 VIPCustomer 생성자가 호출되므로 클래스 변수가 위와 같이 메모리에 만들어 집니다.
- 그런데 클래스의 자료형이 Customer로 한정되었습니다. 클래스가 형 변환이 되었을 때는 선언한 클래스형에 기반하여 멤버 변수와 메서드에 접근할 수 있습니다.
- 따라서 이 vc 참조 변수가 가리킬 수 있는 변수와 메서드는 Customer 클래스의 멤버뿐 입니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D8.png'>

- 하위 클래스의 인스턴스가 상위 클래스로 형 변환되는 과정이 묵시적으로 이루어진다. - <a href='https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2#%EB%8B%A4%ED%98%95%EC%84%B1'>다형성 참조</a>

## 메서드 오버라이딩
- 상위 클래스에 젖의한 메서드가 하위 클래스에서 구현할 내용과 맞지 않을 경우 하위 클래스에서 이 메서드를 재정의할 수 있습니다.
- 이를 메서드 오버라이딩(method overriding)이라고 합니다.
- 오버라이딩을 하려면 반환형, 메서드 이름, 매개 변수, 매개변수 자료형이 반드시 같아야 합니다.
- 그렇지 않다면 재정의한 메서드를 기존 메서드와 다른 메서드로 인식합니다.

#### VIP 고객 클래스의 제품 가격 메서드 재정의하기
- VIPCustomer 클래스에서 calPrice() 메서드를 재정의해 봅시다.

#### day08_10/inheritance/VIPCustomer.java
```
...
@Override
public int calcPrice(int price) {
	bonusPoint += price * bonusRatio;
		
	return price - (int)(price * saleRatio); // 할인된 가격을 계산하여 반환
}
...
```
- 하위클래스 VIPCustomer에서 calPrice() 메서드를 재정의했습니다. 
- 상위 클래스의 calPrice()에서드와 매개변수의 자료형 및 개수가 같고, 반환형도 int형으로 같습니다. 다만 할인율을 계산하여 정가에서 뺀 후 세일가격을 반환하도록 코드가 변경되었습니다.

- 상위 클래스의 메서드를 재정의 할떄는 메서드 이름을 직접 써도 되고, 이클립스의 기능을 활용할 수 있습니다.
- 코드에서 오른쪽 마우스 버튼을 누르고 \[Source -> Override/Implement Methods...\]을 누르면 다음과 같은 화면이 나옵니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EC%83%81%EC%86%8D10.png'>

- 상위 클래스 Customer의 메서드 중에서 재정의 할 메서드를 선택할 수 있습니다.

> <a href='https://github.com/yonggyo1125/curriculum300H/tree/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/16%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%97%B4%EA%B1%B0%ED%98%95%2C%20%EC%95%A0%EB%84%88%ED%85%8C%EC%9D%B4%EC%85%98' target='_blank'>애너테이션(Annotation)</a><br>애너테이션은 영어로는 주석이라는 의미 입니다. @기호와 함께 사용하며, '@애노테이션 이름'으로 표현합니다. 자바에서 제공하는 **애너테이션은 컴파일러에게 특정한 정보를 제공해 주는 역할**을 합니다. 예를 들어 **@Override는 이 메서드가 재정의된 메서드임을 컴파일러에게 알려줍니다.** 만약 메서드의 선언부가 다르다면 컴파일된 오류가 발생하여 프로그래머의 실수를 막아 줍니다. 이렇게 미리 정의되어 있는 애너테이션을 표준 애너테이션이라고 합니다. 

#### 주로 사용하는 표준애너테이션

|애노테이션|설명|
|-----|--------|
|@Override|재정의된 메서드라는 정보 제공|
|@FunctionalInterface|함수형 인터페이스라는 정보 제공|
|@Deprecated|이후 버전에서 사용되지 않을 수 있는 변수, 메서드에 사용됨|
@SuppressWarnings|특정 경고가 나타나지 않도록 함|


#### day08_10/inheritance/OverrideTest1.java
```
package day08_10.inheritance;

public class OverrideTest1 {

	public static void main(String[] args) {
		Customer customerLee = new Customer(10010, "이순신");
		customerLee.bonusPoint = 1000;
		
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신", 12345);
		customerKim.bonusPoint = 10000;
		
		int price = 10000;
		System.out.println(customerLee.getCustomerName() + " 님이 지불해야 하는 금액은 " + customerLee.calcPrice(price) + "원 입니다.");
		System.out.println(customerKim.getCustomerName() + " 님이 지불해야 하는 금액은 " + customerKim.calcPrice(price) + "원 입니다.");
	}
}

실행결과

이순신 님이 지불해야 하는 금액은 10000원 입니다.
김유신 님이 지불해야 하는 금액은 9000원 입니다.
```

### 묵시적 형변환과 메서드 재정의
```
Customer vc = new VIPCustomer("10030", "나몰라", 2000);
vc.calcPrice(10000);
```

- 묵시적 형 변환에 의해 VIPCustomer가 Customer형으로 변환되었습니다.
- 그리고 나서 calcPrice() 메서드가 호출되었습니다. 
-  calcPrice()는 하위 클래스에서 재정의된 메서드이며 Customer 클래스와 VIPCustomer 클래스에 모두 존재합니다. 
- Customer형으로 선언되었다고 하더라도 vc.calcPrice(10000)은 **VIPCustomer에서 재정의된 메서드가 호출**됩니다.<br>(**멤버 변수와 메서드는 선언한 클래스형에 따라 호출됩니다.**)

- 상위 클래스와 하위 클래스에 같은 이름의 메서드가 존재할 때 호출되는 메서드는 인스턴스에 따라 결정됩니다.
- 선언한 클래스형이 아닌 생성된 인스턴스의 메서드를 호출하는 것, 이렇게 메서드가 호출되는 기술을 '**가상 메서드**(virtual method)'라 합니다.

### 가상 메서드
- 자바의 클래스는 멤버변수와 메서드로 이루어져 있습니다. 클래스를 생성하여 인스턴스가 만들어 지면 멤버변수는 힙 메모리에 위치합니다.
- 그러나 변수가 사용하는 메모리와 메서드가 사용하는 메모리는 다릅니다.
- 변수는 인스턴스가 생성될 떄마다 생성되지만 실행해야 할 명령 집합이기 때문에 인스턴스가 달라도 같은 로직을 수행합니다.
- 즉, 같은 객체의 인스턴스를 여러 개 생성한다고 해서 메서드도 여러 개 생성되지 않습니다.

#### day08_10/virtualfunction/TestA.java
```
package day08_10.virtualfunction;

public class TestA {
	int num;
	
	void aaa() {
		System.out.println("aaa() 출력");
	}
	
	public static void main(String[] args) {
		TestA a1 = new TestA();
		a1.aaa();
		TestA a2 = new TestA();
		a2.aaa();
	}
}

실행결과

aaa() 출력
aaa() 출력
```
- 상기 코드가 실행되는 메모리 상태를 그림으로 그리면 다음과 같습니다.
<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EA%B0%80%EC%83%81%EB%A9%94%EC%84%9C%EB%93%9C1.png'>

- main() 함수가 실행되면 지역 변수는 스택 메모리에 위치합니다.
- 각 참조 변수 a1, a2가 가리키는 인스턴스는 힙 메모리에 생성됩니다.
- 메서드의 명령 집합은 **메서드 영역**(코드 영역)에 위치합니다.
- 우리가 메서드를 호출하면 메서드 영역의 주소를 참조하여 명령이 실행됩니다. 따라서 인스턴스가 달라도 동일한 메서드가 호출됩니다.

#### 가상 메서드의 원리 
- 일반적으로 프로그램에서 메서드를 호출한다는 것은 그 메서드의 명령 집합이 있는 메모리 위치를 참조하여 명령을 실행하는 것입니다. 
- 그런데 가상메서드의 경우에는 **가상 메서드 테이블**이 만들어집니다.
- 가상메서드 테이블은 **각 메서드 이름**과 **실제 메모리 주소**가 짝을 이루고 있습니다.
- 어떤 메서드가 호출되면 이 테이블에서 주소 값을 찾아서 해당 메서드의 명령을 수행합니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EA%B0%80%EC%83%81%EB%A9%94%EC%84%9C%EB%93%9C2.png'>

- calcPrice() 메서드는 두 클래스에서 서로 다른 메서드 주소를 가지고 있습니다. 이렇게 재정의된 메서드는 실제 인스턴스에 해당하는 메서드가 호출됩니다.
- showCustomerInfo()와 같이 재정의되지 않은 메서드인 경우는 메서드 주소가 같으며 상위 클래스의 메서드가 호출됩니다.

#### day08_10/inheritance/OverrideTest3.java
```
package day08_10.inheritance;

public class OverrideTest3 {
	public static void main(String[] args) {
		int price = 10000;
		
		Customer customerLee = new Customer(10010, "이순신");
		System.out.println(customerLee.getCustomerName() + " 님이 지불해야 하는 금액은" + customerLee.calcPrice(price) + "원 입니다.");
		
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신", 12345);
		System.out.println(customerKim.getCustomerName() + " 님이 지불해야 하는 금액은 " + customerKim.calcPrice(price) + "원 입니다.");
		
		Customer vc = new VIPCustomer(10030, "나몰라", 2000);
		System.out.println(vc.getCustomerName() + " 님이 지불해야 하는 금액은" + vc.calcPrice(10000) + " 원 입니다.");
	}
}

실행결과

이순신 님이 지불해야 하는 금액은10000원 입니다.
김유신 님이 지불해야 하는 금액은 9000원 입니다.
나몰라 님이 지불해야 하는 금액은9000 원 입니다.
```
- VIPCustomer로 생성하고  Customer형으로 변환한 vc는 원래 Customer형 메서드가 호출되는 것이 맞지만, 가상 메서드 방식에 의해 VIPCustomer 인스턴스의 메서드가 호출되어 할인가격 9,000원이 출력됩니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EA%B0%80%EC%83%81%EB%A9%94%EC%84%9C%EB%93%9C3.png'>

- 상위 클래스(Customer)에서 선언한 calcPrice() 메서드가 있고 이를 하위클래스(VIPCustomer)에서 재정의한 상태에서 하위 클래스 인스턴스(vc)가 상위 클래스로 형 변환이 되었습니다.
- 이때 vc.calcPrice()가 호출되면, vc 변수를 선언할 때 사용한 자료형(Customer)의 메서드가 호출되는 것이 아니라 생성된 인스턴스(VIPCustomer)의 메서드가 호출됩니다.
- 이를 **가상 메서드**라고 합니다. **자바의 모든 메서드는 가상메서드**입니다.


## 다형성

### 다형성이란?
- 다형성이란 하나의 코드가 여러 자료형으로 구현되어 실행되는 것을 말합니다.

> 다형성은 추상 클래스, 인터페이스에서 구현됩니다. 또한 안드로이드, 스트링 등 자바 기반의 프레임워크에서 응용할 수 있는 객체 지향 프로그래밍의 중요한 개념입니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EB%8B%A4%ED%98%95%EC%84%B11.png'>

#### day08_10/polymorphism/AnimalTest1.java
```
package day08_10.polymorphism;

class Animal {
	public void move( ) {
		System.out.println("동물이 움직입니다.");
	}
}

class Human extends Animal {
	public void move() {
		System.out.println("사람이 두 발로 걷습니다.");
	}
}

class Tiger extends Animal {
	public void move() {
		System.out.println("호랑이가 네 발로 뜁니다.");
	}
}

class Eagle extends Animal {
	public void move() {
		System.out.println("독수리가 하늘을 납니다.");
	}
}

public class AnimalTest1 {
	public static void main(String[] args) {
		AnimalTest1 aTest = new AnimalTest1();
		aTest.moveAnimal(new Human());
		aTest.moveAnimal(new Tiger());
		aTest.moveAnimal(new Eagle());
	}
	
	public void moveAnimal(Animal animal) {
		animal.move();
	}
}

실행결과

사람이 두 발로 걷습니다.
호랑이가 네 발로 뜁니다.
독수리가 하늘을 납니다.
```
- 테스트를 하기 위해 AnimalTest1 클래스에 moveAnimal(); 메서드를 만들었습니다. 이 메서드는 어떤 인스턴스가 매개변수로 넘어와도 모두 Animal형으로 변환합니다.
- 예) Animal ani = new Human();
- Animal에서 상속받은 클래스가 매개변수로 넘어오면 모두 Animal형으로 변환되므로 animal.move() 메서드를 호출할 수 있습니다.
- 가상 메서드의 원리에 따라 animal.move가 아닌 매개변수로 넘어온 실제 인스턴스의 메서드입니다.
- animal.move() 코드는 변함이 없지만 어떤 매개변수가 넘어왔느냐에 따라 출력문이 달라집니다. 이것이 다형성 입니다.

<img src='https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/8~10%EC%9D%BC%EC%B0%A8(9h%20-%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D2/images/%EB%8B%A4%ED%98%95%EC%84%B12.png'>

### 다형성의 장점 
다형성을 활용한 프로그램의 확장성 
	- 상위 클래스에서 공통 부분의 메서드를 제공하고, 하위 클래스에서는 그에 기반한 추가 요소를 덧붙여 구현하면 코드 양도 줄어들고 유지보수도 편리합니다.
	- 필요에 따라 상속받은 모든 클래스를 하나의 상위 클래스로 처리할 수 있고 다형성에 의해 각 클래스의 여러 가지 구현을 실행 할 수 있으므로 프로그램을 쉽게 확장할 수 있습니다.
	- 다형성을 잘 활용하면 유연하면서도 구조화된 코드를 구현하여 확장성 있고 유지보수하기 좋은 프로그램을 개발할 수 있습니다.
	
#### day08_10/polymorphism/Customer.java - 다형성을 활용해 VIP 고객 클래스 완성하기
```
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
```
- 기존 Customer 클래스와 달라진 점은 initCustomer() 메서드가 있습니다.
- 이; 메서드는 클래스의 멤버 변수를 초기화하는데, Customer 클래스를 생성하는 두 생성자에서 공통으로 사용하는 코드이므로 메서드로 분리하여 호출했습니다.

### day08_10/polymorphism/VIPCustomer.java - 다형성을 활용해 VIP 고객 클래스 완성하기
```
package day08_10.polymorphism;

public class VIPCustomer extends Customer {
	private int agentID;
	double saleRatio;
	
	public VIPCustomer(int customerID, String customerName, int agentID){
		super(customerID, customerName);
	
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;
	}
	
	// 지불 가격 메서드 재정의
	public int calcPrice(int price){
		bonusPoint += price * bonusRatio;
		return price - (int)(price * saleRatio);
	}
	
	// 고객 정보 출력 메서드 재정의
	public String showCustomerInfo(){
		return super.showCustomerInfo() + " 담당 상담원 번호는 " + agentID + "입니다";  
	}

	public int getAgentID(){
		return agentID;
	}
}
```
- VIPCustomer 클래스에서 calcPrice() 메서드와 showCustomerInfo() 메서드를 재정의했습니다.
- 일반 고객 클래스에서 calcPrice()메서드는 정가를 그대로 반환했지만, VIPCustomer 클래스에서는 할인율을 반영한 지불 가격을 반환합니다. 또 일반 고객 클래스에서, showCustomerInfo()메서드는 고객 등급과 이름만 출력했지만 VIPCustomer 클래스에서는 담당 상담원 번호까지 출력합니다.

### day08_10/polymorphism/CustomerTest.java - 다형성을 활용해 VIP 고객 클래스 완성하기
```
package day08_10.polymorphism;

public class CustomerTest {
	public static void main(String[] args) {
		Customer customerLee = new Customer();
		customerLee.setCustomerID(10010);
		customerLee.setCustomerName("이순신");
		customerLee.bonusPoint = 1000;
		System.out.println(customerLee.showCustomerInfo());
		
		// VIPCustomer를 Customer형으로 선언
		Customer customerKim = new VIPCustomer(10020, "김유신", 12345);
		customerKim.bonusPoint = 1000;
		System.out.println(customerKim.showCustomerInfo());
		
		
		System.out.println("====== 할인율과 보너스 포인트 계산 =======");
		
		int price = 10000;
		int leePrice = customerLee.calcPrice(price);
		int kimPrice = customerKim.calcPrice(price);
		System.out.println(customerLee.getCustomerName() +" 님이 " + leePrice + "원 지불하셨습니다.");
		System.out.println(customerLee.showCustomerInfo());
		System.out.println(customerKim.getCustomerName() +" 님이 " + kimPrice + "원 지불하셨습니다.");
		System.out.println(customerKim.showCustomerInfo());
	}
}
```
- 출력 결과를 보면 10,000원 짜라 상품을 구입했을 때 등급에 따라 다른 할인율과 포인트 적립이 이루어지는 것을 알 수 있습니다.
- 그런데 여기에서 customerLee와 customerKim은 모두 Customer형으로 선언되었고, 고객의 자료형은 Customer형으로 동일하지만 할인율과 보너스 포인트는 각 인스턴스의 메서드에 맞게 계산되었습니다.
- 즉, 상속 관계에 있는 상위 클래스와 하위 클래스는 같은 상위 클래스 자료형으로 선언되어 생성할 수 있지만 재정의된 메서드는 각각 호출될 뿐만 아니라 이름이 같은 메서드가 서로 다른 역할을 구현하고 있음을 알 수 있습니다.


## 다형성 활용하기

## 다운 캐스팅과 instanceof 

* * * 
# 추상 클래스

## 추상 클래스

## 템플릿 메서드

## 템플릿 메서드 응용하기

## final 예약어

* * * 
# 인터페이스

## 인터페이스란?

## 인터페이스와 다형성

## 인터페이스의 요소 살펴보기

## 인터페이스 활용하기

* * * 
# 내부 클래스

## 내부 클래스 정의와 유형

## 인스턴스 내부 클래스

## 정적 내부 클래스

## 지역 내부 클래스

## 익명 내부 클래스


