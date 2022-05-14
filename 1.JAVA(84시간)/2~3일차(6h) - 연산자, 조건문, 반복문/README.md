# 강의 동영상 링크
[동영상 링크](https://drive.google.com/drive/folders/16zO3HIK_tugNUUsvt5-Qp2yt1zCR0aD6?usp=sharing)

# 연산자

## 항과 연산자
- 연산에 사용하는 기호를 **연산자**(operator)라고 합니다.
- 연산에 사용하는 값을 **항**(operand)라고 합니다.
- 연산자는 항의 개수에 따라 단항연산자, 이항연산자, 삼항연산자로 나눌 수 있습니다.

|연산자|설명|연산 예|
|----|-----|-----|
|단항 연산자|항이 한 개인 연산자|++num|
|이항 연산자|항이 두 개인 연산자|num1 + num2;|
|삼항 연산자|항이 세 개인 연산자|(5 > 3)?1:0;|


## 대입 연산자
- 변수에 값을 대입하는 연산자입니다.
- 대입연산자는 이항 연산자 중 **우선순위가 가장 낮은 연산자**입니다.
- 하나의 문장에 여러 연산자가 있을 때 모든 연산을 다 끝낸 후 마지막에 연산 결과를 변수에 대입하는 것입니다.
```
왼쪽 변수 = 오른쪽 변수(또는 식)

int age = 24;  // 나의를 의미하는 age 변수에 값 24를 대입함 
```
- 오른쪽 변수 값이나 식의 연산 결과를 왼쪽 변수에 대입합니다.

## 부호 연산자
- 부호연산자는 +, - 두 가지가 있습니다.
- 더하기, 빼기 연산에 쓰는 이항 연산자이면서 부호를 나타내는 단항 연산자로 사용합니다.

|연산자|기능|연산 예|
|----|-----|-----|
|+|변수나 상수 값을 양수로 만듭니다.|+3|
|-|변수나 상수 값을 음수로 만듭니다.|-3|

```
int num = 10;

System.out.println(+num); // 값 10이 그대로 출력됨
System.out.println(-num); // 값 10에 -가 붙어서 -10이 출력되지만 num 값이 실제로 바뀌지는 않음.
System.out.println(num); // 값 10이 그대로 출력됨

num = -num; // num 값을 음수로 바꿔서 다시 num으로 대입함
System.out.println(num); // 값 -10이 출력됨 
```

## 산술 연산자
- 사칙 연산에 사용하는 연산자가 산술 연산자 입니다.

|연산자|기능|연산 예|
|----|-----|-----|
|+|두 항을 더합니다.|5 + 3|
|-|앞에 있는 항에서 뒤에 있는 항을 뺍니다.|5 - 3|
|\*|두 항을 곱합니다.|5 \* 3|
|/|앞에 있는 항에서 뒤에 있는 항을 나누어 몫을 구합니다.|5 / 3|
|%|앞에 있는 항에서 뒤에 있는 항을 나누어 나머지는 구합니다.|5 % 3|

### 산술 연산자의 우선순위
- 산술 연산자의 우선순위는 일반 수학의 산술연산과 같습니다.

#### day02_03/operator/OperationEx1.java 
```
package day02_03.operator;

public class OperationEx1 {
	public static void main(String[] args) {
		int mathScore = 90;
		int engScore = 70;
		
		int totalScore = mathScore + engScore; // 총점 구하기
		System.out.println(totalScore);
		
		double avgScore = totalScore / 2.0; // 평균 구하기
		System.out.println(avgScore);
	}
}

실행결과
160
80.0
```


## 증가감소 연산자
- 증가,감소 연산자는 단항 연산자 입니다.
- 연산자 앞이나 뒤에 사용하며 값을 1만큼 늘리거나 1만큼 줄입니다.

|연산자|기능|연산 예|
|----|-----|-----|
|++|항의 값에 1을 더합니다.|val = ++num; // 먼저 num에 값이 1 증가한 후 val변수에 대입<br>val = num++; // val 변수에 기존 num 값을 먼저 대입한 후 값 1 증가|
|--|항의 값에서 1을 뺍니다.|val = --num; // 먼저 num값이 1 감소한 후 val 변수에 대입<br>val = num--; // var 변수에 기존 num 값을 먼저 대입한 후 num값 1 감소|

```
int value = 10;
int num = ++value; // num에 11이 대입됨
```

```
int value = 10;
int num = value++; // num에 10이 대입됨
```

### day02_03/operator/OperationEx2.java
```
package day02_03.operator;

public class OperationEx2 {
	public static void main(String[] args) {
		int gameScore = 150;
		
		int lastScore1 = ++gameScore; 
		System.out.println(lastScore1); // 151
		
		int lastScore2 = --gameScore;
		System.out.println(lastScore2); // 150
	}
}

실행결과
151
150
```

## 관계 연산자
- 항이 두 개인 이행 연산자 입니다.
- 두 개의 항 중 어느 것이 더 큰지, 작은지, 같은지등의 여부를 검사합니다.
- 관계 연산자의 결과 값은 참(true) 또는 거짓(false)로 반환됩니다.

|연산자|기능|연산 예|
|----|-----|-----|
|>|왼쪽 항이 크면 참을, 아니면 거짓을 반환합니다.|num > 3;|
|<|왼쪽 항이 작으면 참을, 아니면 거짓을 반환합니다.|num < 3;|
|>=|왼쪽 항이 오른쪽 항보다 크거나 같으면 참, 아니면 거짓을 반환합니다.|num >= 3;|
|<=|왼쪽 항이 오른쪽 항보다 작거나 같으면 참, 아니면 거짓을 반환합니다.|num <= 3;|
|==|두 개의 항의 값이 같으면 참, 아니면 거짓을 반환합니다.|num == 3;|
|!=|두 개의 항이 다르면 참, 아니면 거짓을 반환합니다.|num != 3;|

```
int myAge = 27;
boolean value = (myAge > 25);
System.out.println(value); // true
```

## 논리 연산자
- 두 명제가 모두 참이면 논리 곱은 참이고, 두 명제 중 하나만 참이면 논리 합은 참입니다. 
- 참의 부정은 거짓, 거짓의 부정은 참입니다.
- 이러한 논리 연산을 프로그래밍 언어로 구현한 연산자가 논리 연산자입니다.
- 논리 연산자는 주로 관계연산자와 함께 사용합니다.
- 관계연산자의 우선순위가 논리연산자보다 높으므로, 관계연산자의 결과 값을 기반으로 논리 연산자의 결과 값을 계산합니다.

|연산자|기능|연산 예|
|-----|-----|-----|
|&&<br>(논리 곱)|두 항이 모두 참인 경우에만 결과 값이 참입니다. 그렇지 않은 경우는 거짓입니다.|boolean val = (5 > 3) && (5 > 2);|
|\|\|<br>(논리 합)|두 항 중 하나의 항만 참이면 결과 값은 참입니다. 두 항이 모두 거짓이면 결과 값은 거짓입니다.|boolean val = (5 > 3) \|\| (5 > 2);|
|!<br>(부정)|단일 연산자입니다. 참인 경우는 거짓으로 바꾸거, 거짓인 경우는 참으로 바꿉니다.|boolean val = !(5 > 3);|

```
int num1 = 10;
int num2 = 20;

boolean flag = (num1 > 0) && (num2 > 0);
System.out.println(flag); // flag는 참

flag = (num1 < 0) && (num2 > 0);
System.out.println(flag); // flag는 거짓 

flag = (num1 < 0) || (num2 > 0);
System.out.println(flag); // flag는 참
```

### 논이연산에서 모든 항이 실행되지 않는 경우 - 단락회로 평가
#### day02_03/operator/OperationEx3.java
```
package day02_03.operator;

public class OperationEx3 {
	public static void main(String[] args) {
		int num1 = 10;
		int i = 2;
		
		// 논리 곱에서 앞 항의 결과 값이 거짓이므로 ((i = i + 2) < 10); 문장은 실행되지 않는다.
		boolean value = ((num1 = num1 + 10) < 10) && ((i = i + 2) < 10);
		System.out.println(value);
		System.out.println(num1);
		System.out.println(i);
		
		// 논리 합에서 앞 항의 결과 값이 참이므로 ((i = i + 2) < 10);은 실행되지 않음
		value = ((num1 = num1 + 10) > 10) || ((i = i + 2) < 10);
		System.out.println(value);
		System.out.println(num1);
		System.out.println(i);
	}
}

실행결과
false
20
2
true
30
2
```

## 복합 대입 연산자
- 복합 대입 연산자란 대입 연산자의 다른 연산자를 조합해 하나의 연산자처럼 사용하는 연산자입니다.
- 산술연산자, 비트연산자와 함게 사용하여 코드를 간결하게 표현할 수 있습니다.
- 대입 연산자는 우선순위가 가장 낮은 연산자입니다. 연산이 모두 끝난 후 마지막으로 결과 같을 변수에 대입합니다.
- 복합 대입 연산자 역시 연산한 결과 값을 변수에 대입합니다.
- 복합 대입 연산자는 특히 산술 연산자와 함께 자주 사용됩니다.

|연산자|기능|연산 예|
|-----|-----|-----|
|+=|두 항의 값을 더해서 왼족 항에 대입합니다.|num1 += 2;<br>num1 = num1 + 2;와 같음|
|-=|왼쪽 항에서 오른족 항을 빼서 그 값을 왼쪽 항에 대입합니다.|num1 -= 2;<br>num1 = num1 - 2;와 같음|
|\*=|두 항의 값을 곱해서 왼쪽 항에 대입합니다.|num1 \*= 2;<br>num1 = num1 \* 2;와 같음|
|/=|왼쪽 항을 오른쪽 항으로 나누어 그 몫을 왼쪽 항에 대입합니다.|num1 /= 2;<br>num1 = num1 / 2;와 같음|
|%=|왼쪽 항을 오른쪽 항으로 나누어 그 나머지를 왼쪽 항에 대입합니다.|num1 %= 2;<br>num1 = num1 % 2;와 같음|

> 복합 대입 연산자를 사용하면 변수를 반복적으로 사용하지 않아도 되는 장점이 있습니다.

## 조건 연산자
- 조건 연산자는 연산에 필요한 항의 개수가 세 개입니다. 그래서 **삼항 연산자**라고 합니다.

|연산자|기능|연산 예|
|------|-----|-----|
|조건식 ? 결과1 : 결과2;|조건식이 참이면 결과1, 조건식이 거짓이면 결과2가 선택됩니다.|int num = (5 > 3)?10:20|

#### day02_03/operator/OperationEx4.java
```
package day02_03.operator;

public class OperationEx4 {
	public static void main(String[] args) {
		int fatherAge = 45;
		int motherAge = 47;
		
		char ch; 
		ch = (fatherAge > motherAge)? 'T':'F';
		
		System.out.println(ch);
	}
}

실행결과
F
```

## 연산자 우선순위
- 연산자에는 우선순위가 있습니다.
- 우선순위에 따라 컴퓨터가 연산을 수행하고 그 결과가 달라집니다.
	- 단항 연산자가 가장 높고 이항, 삼항 연산자 순서입니ㅏㄷ.
	- 대입 연산자의 우선순위가 가장 낮습니다.
	- 산술, 관계, 논리, 대입 연산자 순서로 우선순위를 가지며, ()의 우선순위가 가능 높습니다.


|우선순위|형|연산자|연산방향|
|-----|----|-------|----|
|1|일차식|(), []| \-\-\-\->|
|2|단항|! ++ -- + -|<\-\-\-\-|
|3|산술|% /| \-\-\-\->|
|4|산술|+ -| \-\-\-\->|
|5|관계|< > <= >=| \-\-\-\->|
|6|관계|== !=| \-\-\-\->|
|7|논리 곱|&&| \-\-\-\->|
|8|논리 합|(\|\|| \-\-\-\->|
|9|조건|?:| \-\-\-\->|
|10|대입|= += -= \*= %= /=|<\-\-\-\-|

* * * 
# 조건문 

## 조건문이란?
- 조건문이란 주어진 조건에 따라 다른 문장을 선택할 수 있도록 프로그래밍 하는 것
```
만약(나이가 8살 이상이라면) {
	학교에 다닙니다.
} 
그렇지 않다면 {
	학교에 다니지 않습니다.
}
```

## if문과 if-else문

- 조건문의 가장 단순한 형식
- 주어진 조건식이 '**참**'일 경우에 중괄호 안에 있는 문장을 수행합니다.
- 조건식에는 결과가 참, 거짓으로 판별되는 식이나, 참, 거짓의 값을 가진 변수, 상수를 사용할 수 있습니다.
- 연산의 결과가 참, 거짓이 되는 관계 연산자를 자주 사용합니다.

```
if (조건식) {
	수행문; // 조건식이 참일 경우 이 문장을 수행 
}
```
```
int age = 10;
if (age >= 0) {  // age 값이 8이상이면 
	System.out.println("학교에 다닙니다.");  // 이 문장을 수행함
}
```

- 조건식을 만족하는 경우와 만족하지 않는 경우를 모두 나나탤 때는 if-else문을 사용합니다.
- else문에는 조건식을 사용하지 않습니다.

```
if (조건식) {
	수행문1: // 조건식이 참일 경우에 이 문장을 수향
} 
else {
	수행문2 : // 조건식이 거짓일 경우에 이 문장을 수행
}
```

#### day02_03/ifexample/IfExample1.java
```
package day02_03.ifexample;

public class IfExample1 {
	public static void main(String[] args) {
		int age = 7;
		if(age >= 8) {
			System.out.println("학교에 다닙니다.");
		} else {
			System.out.println("학교에 다니지 않습니다.");
		}
	}
}

실행결과
학교에 다니지 않습니다.
```

## if-else if-else 문 
- 하나의 상황에 조건이 여러 개인 경우는 if-else if-else문으로 표현할 수 있습니다.
```
if (조건식2) {
	수행문1: // 조건식1이 참일 경우에 수행함
} else if (조건식2) {
	수행문2; // 조건식2가 참일 경우에 수행함
} else if (조건식3) {
	수행문3; // 조건식3이 참일 경우에 수행함
} else {
	수행문4; // 위의 조건이 모두 해당하지 않는 경우에 수행함
}

수행문5;  // if-else if-else문이 끝난 후에 수행함
```

#### day02_03/ifexample/IfExample2.java
```
package day02_03.ifexample;

public class IfExample2 {
	public static void main(String[] args) {
		int age = 0;
		int charge;
		
		if (age < 8) {
			charge = 1000;
			System.out.println("취학 전 아동입니다.");
		} else if(age < 14) {
			charge = 2000;
			System.out.println("초등학생입니다.");
		} else if (age < 20) {
			charge = 2500;
			System.out.println("중, 고등학생입니다.");
		} else {
			charge = 3000;
			System.out.println("일반인입니다.");
		}
		System.out.println("입장료는 " + charge + "원 입니다.");
	}
}

실행결과
취학 전 아동입니다.
입장료는 1000원 입니다.
```
> 출력문에서 +를 사용하면 여러 단어를 연결하여 출력할 수 있습니다. 

### if-else if문과 if-if문의 차이

#### day02_03/ifexample/IfExample3.java
```
package day02_03.ifexample;

public class IfExample3 {
	public static void main(String[] args) {
		int age = 0;
		int charge;
		
		if (age < 8) {
			charge = 1000;
			System.out.println("취학 전 아동입니다.");
		} 
		if(age < 14) {
			charge = 2000;
			System.out.println("초등학생입니다.");
		} 
		if (age < 20) {
			charge = 2500;
			System.out.println("중, 고등학생입니다.");
		} else {
			charge = 3000;
			System.out.println("일반인입니다.");
		}
		System.out.println("입장료는 " + charge + "원 입니다.");
	}
}

실행결과
취학 전 아동입니다.
초등학생입니다.
중, 고등학생입니다.
입장료는 2500원 입니다.
```
- if-else if문은 하나의 조건을 만족하면 나머지 조건을 비교하지 않고 다음 수행문으로 넘어갑니다. 하지만 if문으로 이루어진 코드는 조건마다 각각 비교합니다.

## 조건문과 조건 연산자
- if-else문은 **조건 연산자**로도 구현할 수 있습니다. 

if-else문
```
if (a > b)
	max = a;
else 
	max = b;
```

조건  연산자
```
max = (a > b)? a : b;
```
> 간단한 조건문이고 선택이 두가지만 있는 경우 종종 조건 연산자도 사용한다


## switch-case문
- 조건문을 구현할 때 if 문을 사용하면 번거로운 경우가 있습니다.
- switch-case문은 주로 조건이 하나의 변수 ㄱ밧이나 상수 값으로 구분되는 경우 사용합니다.
- 조건식의 결과가 정수 또는 문자열 값이고 그 값에 따라 수행되는 경우가 각각 다른 경우에는 switch-case 문으로 구헝하는 것이 코드도 깔끔하고 가독성도 좋습니다.
- case문에는 여러 문장이 있어도 {}를 여러 번 사용하지 않습니다.

if-else if-else 
``` 
if (rank ==1) {
	medalColor = 'G';
} else if (rank == 2) {
	medalColor = 'S';
} else if (rank == 3) {
	medalColor = 'B';
} else {
	medalColor = 'A';
}

```

switch-case 
```
switch(rank) {
	case 1: medalColor = 'G';
		break;
	case 2: medalColor = 'S';
		break;
	case 3: medalColor = 'B';
		break;
	default : medalColor = 'A';
}
```
```
switch(조건) {
	case 값1: 수행문1;
		break;
	case 값2: 수행문2;
		break; 
	case 값3: 수행문3;
		break;
	default: 수행문4;
}
```
#### day02_03/ifexample/SwitchCase1.java 
```
package day02_03.ifexample;

public class SwitchCase1 {
	public static void main(String[] args) {
		int ranking = 1;
		char medalColor;
		
		switch(ranking) {
			case 1: medalColor = 'G';
				break;
			case 2: medalColor = 'S';
				break;
			case 3: medalColor = 'B';
				break;
			default : 
					medalColor = 'A';
		}
		
		System.out.println(ranking + "등 메달의 색깔은 " + medalColor + " 입니다.");
	}
}

실행결과
1등 메달의 색깔은 G 입니다.
```

### case문 동시에 사용하기
```
case 1: case 3: case 5: case 7: case 8: case 10: case 12: day = 31;
	break;
case 4: case 6: case 9: case 11: day = 30;
	break;
case 2: day = 28;
	break;
```

### switch-case문에서  break의 역할
- break문은 switch-case문의 수행을 멈추고 빠져나가도록 합니다.
- break를 쓰지 않으면 다음 break를 만날때까지 다음 case 부분이 실행됩니다.

### case문에 문자열 사용하기
- JDK1.7부터는 switch-case문의 case값에 정수 값 뿐 아니라 문자열도 사용할 수 있습니다.
- 이전에는 문자열을 사용할 수 없었기 때문에 문자열을 비교하는 equals()메서드라는 것을 이용해야 했습니다.

JDK1.7 이전
``` 
if (medal.equals("Gold")) {
	...
}
```

#### day02_03/ifexample/SwitchCase2.java 
```
package day02_03.ifexample;

public class SwitchCase2 {
	public static void main(String[] args) {
		String medal = "Gold";
		
		switch(medal) {
			case "Gold":
				System.out.println("금메달입니다.");
				break;
			case "Silver":
				System.out.println("은메달입니다.");
				break;
			case "Bronze":
				System.out.println("동메달입니다.");
				break;
			default : 
				System.out.println("메달이 없습니다.");
		}
	}
}


실행결과
1등 메달의 색깔은 G 입니다.
```

* * * 
# 반복문 

#### day02_03/loopexample/BasicLoop.java
```
package day02_03.loopexample;

public class BasicLoop {
	public static void main(String[] args) {
		int num = 1;
		num += 2;
		num += 3;
		num += 4;
		num += 5;
		num += 6;
		num += 7;
		num += 8;
		num += 9;
		num += 10;
		
		System.out.println("1부터 10까지의 합은 " + num + " 입니다.");
	}
}

실행결과
1부터 10까지의 합은 55 입니다.
```

- 상기 코드는 1~10까지 더하여 합을 구하는데 효율적이지 않습니다. 
- 반복되는 일을 효율적으로 처리하기 위해 사용하는 것이 **반복문** 입니다.


## while문
```
while(조건식) {
	수행문1;  // 조건식이 참인 동안 반복 수행 
}
```
#### day02_03/loopexample/WhileExample1.java
```
package day02_03.loopexample;

public class WhileExample1 {
	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		while(num <= 10) { // num값이 10보다 작거나 같을 동인
			sum += num; // 합계를 뜻하는 sum에 num을 더하고 
			num++; // num에 1씩 더해 나감 
		}
		System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
	}
}

실행결과
1부터 10까지의 합은 55입니다.
```

### while문이 무한히 반복되는 경우
```
while(true) {
	...
}
```
- while문의 구조를 보면 조건식이 참이면 반복합니다.
- while문을 상기 코드와 같이 사용하면 조건이 항상 **참**이 되어 **무한 반복**합니다. 


## do-while문
- while문은 조건을 먼저 검사하기 때문에 조건식이 맞기 않으면 반복 수행이 한 번도 일어나지 않습니다.
- do-while문은 {}안의 문장을 무조건 한 번 수행한 후에 조건식을 검사합니다. 즉, 조건이 만족하는지 여부를 마지막에 검사하는 것입니다.
- 중괄호 안의 문장을 반드시 한 번 이상 수행해야 할 때 while문 대신 do-while문을 사용합니다.

```
do {
	수행문1;
	...
} while(조건식);
```

#### day02_03/loopexample/DoWhileExample.java
```
package day02_03.loopexample;

public class DoWhileExample {
	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		do {
			// 주어진 조건이 참이 아니더라도 무조건 한 번 수행함
			sum += num;
			num++;
		} while(num <= 10);
		
		System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
	}
}

실행결과
1부터 10까지의 합은 55입니다.
```

## for문 
- 반복문 중에서 가장 많이 사용하는 반복문이 for문 입니다.
- for문은 반복문을 구현하는데 필요한 여러 요소(변수의 초기화식, 조건식, 증감식)를 함께 작성하여야 하므로 while문이나 do-while문 보다 구조가 조금 더 복잡합니다.

### for문의 기본구조
```
for(초기화식; 조건식; 증감식) {
	수행문;
}
```
- 초기화식 : for문이 시작할 때 딱 한번만 수행하며 사용할 변수를 초기화합니다.
- 조건식 : 언제까지 반복수행할 것인지 구현합니다.
- 증감식 : 반복 횟수나 for문에서 사용하는 변수 값을 1만큼 늘리거나 줄입니다.

### 1부터 5까지 출력하는 프로그램 예시
![for 문](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/2~3%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%97%B0%EC%82%B0%EC%9E%90%2C%20%EC%A1%B0%EA%B1%B4%EB%AC%B8%2C%20%EB%B0%98%EB%B3%B5%EB%AC%B8/images/for%EB%AC%B8.png)

```
1. 처음 for문이 시작할 때 출력할 숫자인 num을 1로 초기화합니다
```

```
2. 조건식 num <=5를 검사했을 때 num은 1이므로 참입니다.
3. 조건식이 참이기 때문에 for문의 System.out.println(num);을 수행하고 1을 출력합니다.
4. 증감식 num++를 수행하여 num값이 2가 됩니다.
```

```
2. 조건식 num<=5를 검사했을 때 num값은 2이므로 참입니다.
3. 조건식이 참이기 때문에 for문의 System.out.println(num);을 수행하고 2을 출력합니다.
4. 증감식 num++를 수행하여 num값은 3이 됩니다.


... 반복...
```

```
2. 조건식 num <=5를 검사했을 때 num 값은 6이므로 거짓입니다. for문이 끝납니다.
```

#### day02_03/loopexample/ForExample1.java
```
package day02_03.loopexample;

public class ForExample1 {
	public static void main(String[] args) {
		int i;
		int sum;
		for (i = 1, sum = 0; i <= 10; i++) {
			sum += i;
		}
		
		System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
	}
}

실행결과
1부터 10까지의 합은 55입니다.
```

### for문을 자주 사용하는 이유
- for문을 가장 많이 사용하는 이유는 반복 횟수를 관리할 수 있기 때문입니다.

![for문과 while문 비교](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/2~3%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%97%B0%EC%82%B0%EC%9E%90%2C%20%EC%A1%B0%EA%B1%B4%EB%AC%B8%2C%20%EB%B0%98%EB%B3%B5%EB%AC%B8/images/for%EB%AC%B8%EA%B3%BC%20while%EB%AC%B8%20%EB%B9%84%EA%B5%90.png)

- while문으로 작성한 코드를 살펴보면 num의 초기화와 조건 비교, 증감식을 따로 구현했습니다.
- 하지만 for문을 사용하여 구현하면 초기화, 조건비교, 증감식을 한 줄에 쓸 수 있고 가독성도 좋아집니다.
- for문은 배열과 함께 자주 사용합니다. 배열은 자료형이 순서대로 모여있는 구조인데, 배열 순서를 나타내느 색인은 항상 0부터 시작합니다. 따라서 배열의 전체 요소 개수가 n개일 때, 요소 위치는 n-1번째로 표현할 수 있습니다. 
- 이러한 배열의 특성과 증감에 따른 반복을 표현하는 데 적합한 for문의 특성 때문에 for문과 배열을 함께 자주 사용하는 것입니다.

## for문 요소 생략하기
#### 초기화식 생략
```
int = 0; 
for ( ; i < 5; i++) {
	...
}
```

#### 조건식 생략 
```
for(i = 0;  ; i++) {
	sum += i;
	if (sum > 200) break;
}
```

#### 증감식 생략
```
for(i = 0; i < 5;  ) {
	...
	i = (++i) % 10;
}
```

#### 요소 모두 생략
모든 요소를 생략하고 무한 반복하는 경우에 사용합니다.
for ( ;  ; ) {
	...
}
	
## 중첩된 반복문

#### day02_03/loopexample/NestedLoop.java 
```
package day02_03.loopexample;

public class NestedLoop {
	public static void main(String[] args) {
		int dan;
		int times;
		
		for (dan = 2; dan <= 9; dan++) { // 2단부터 9단까지 반복하는 외부 반복문 
			for (times = 1; times <= 9; times++) { // 각 단에서 1~9를 곱하는 내부 반복문
				System.out.println(dan + "X" + times + " = " + dan * times);
			}
			System.out.println();
		}
	}
}
```

## continue문 
- continue문은 반복문과 함께 쓰입니다. 반복문 안에서 continue문을 만나면 이후의 문장은 수행하지 않고 for문의 처음으로 돌아가 증감식을 수행합니다.
- 반복 건너뛰기

#### day02_03/loopexample/ContinueExample.java 
```
package day02_03.loopexample;

public class ContinueExample {
	public static void main(String[] args) {
		int total = 0;
		int num;
		
		for(num = 1; num <= 100; num++) {
			if (num % 2 == 0) {
				continue;
			}
			
			total += num;
		}
		
		System.out.println("1부터 100까지의 홀수의 합은: " + total + "입니다.");
	}
}

수업결과
1부터 100까지의 홀수의 합은: 2500입니다.
```

## break문 
반복문에서 break문을 사용하면 그 지점에서 더 이상 수행문을 반복하지 않고 반복문을 빠져나옵니다.

#### day02_03/loopexample/BreakExample2.java
```
package day02_03.loopexample;

public class BreakExample {
	public static void main(String[] args) {
		int sum = 0;
		int num = 0;
		
		for (num = 0; ; num++) {
			sum += num;
			if (sum >= 100)
				break;
		}
		
		System.out.println("num : " + num);
		System.out.println("sum : " + sum);
		
		sum = 0;
		num = 0;
		while(true) {
			sum += num;
			if (sum >= 100) {
				break;
			}
			
			num++;
		}
		
		System.out.println("num : " + num);
		System.out.println("sum : " + sum);
	}
}

실행결과
num : 14
sum : 105
num : 14
sum : 105
```


## 연습문제
1. 변수 두 개를 선언해서 20과 3.0을 대입하고 두 변수의 사칙연산 결과를 정수로 출력해 보시오


2. 학생 40명이 리조트에 수학여행을 갔습니다. 리조트로 수학여행을 갔습니다. 리조트는 방이 10개 있고 방번호는 0~9번까지 있습니다, 40명의 학생이 각각 번호표를 받아 한 방에 4명씩 배정하여 다음과 같이 출력하시오(예 :  학생1, 방번호 1번)


3. 5층 건물이 있습니다. 1층 약국, 2층 정형외과, 3층 피부과, 4층 치과, 5층 헬스클럽입니다. 건물의 층을 누르면 그 층이 어떤 곳인지 알려주는 엘리베이터가 있을 때 이를 swich ~ case문으로 구현하시오(5층인 경우 ‘5층 헬스클럽입니다.’)


4. 구구단을 홀수 단만 출력하도록 프로그램을 만드시오.
구현 소스 







5. 구구단을 단보다 곱하는 수가 크거나 같은 경우만 출력하는 프로그램을 만들어 보세요.구현 소스 