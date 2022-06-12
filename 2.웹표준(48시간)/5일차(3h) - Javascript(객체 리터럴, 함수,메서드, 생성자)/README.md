# 객체 리터럴
- 객체는 이름과 값을 한쌍을 묶은 데이터를 여러개 모은 것
- 즉, 객체는 데이터 여러개를 하나로 모은 복합 데이터로 연관배열 또는 사전(Diction) 이라고 부릅니다. 
|이름|값|
|----|----|
|suit|"하트"|
|rank|"A"|

- 객체에 포함된 데이터 하나(이름과 값의 쌍)를 가리켜 객체의 프로퍼티라고 부른다
- 프로퍼티 이름 부분을 “프로퍼티 이름” 또는 “키”라고 부릅니다.
- 객체를 생성하는 방법 2가지 : 객체 리터럴 사용, 생성자 함수 사용  

## 객체 리터럴로 객체 생성하기 
```javascript
var card = { suit  : "하트", rank : "A" };
```
- { ... } 부분이 객체 리터럴 이며 객체 리터럴을 변수 card에 대입하고 있음
- 프로퍼티 값은 suit : “하트” 처럼 콜론(:)을 사용해서 구분
- 중괄호({})안에 있는 프로퍼티들은 쉼표(,)로 구분
- 변수에 대입된 객체 안의 프로퍼티 값을 읽거나 쓸 때에는 마침표(.) 연산자 또는 
- 대괄호 ([]) 연산자를 사용 

```javascript
 card.suit // -> 하트
 card['rank'] // -> A 
```

- 객체에 없는 프로퍼티를 읽으려고 시도하면 undefined를 반환
```javascript
card.color // -> undefined
```
- 객체 리터럴 안에 어떠한 프로퍼티도 작성하지 않으면 빈 객체가 생성됨
```javascript
var obj = {};
console.log(obj); // -> Object{}
```

## 프로퍼티 추가와 삭제
- 없는 프로퍼티 이름에 값을 대입하면 새로운 프로퍼티가 추가됨
```javascript
card.value = 14;
console.log(card); // Object {suit : "하트", rank : "A", value : 14 }
```
- delete 연산자를 사용하여 프로퍼티 삭제
```javascript
delete card.rank;
console.log(card); // Object { suit : “하트”, value : 15 }
```

## in 연산자로 프로퍼티가 있는지 확인하기
프로퍼티가 객체에 포함되어 있을때 true, 없을때 false를 반환 
```javascript  
var card = { suite : "하트", rank : "A" };
console.log(”suit” in card); // -> true
console.log(”color” in card); // false
```
## 객체 리터럴 예제
- 예1) 좌표평면의 점을 표현하는 객체
```javascript
var p = { x : 1.0, y : 2.0 };
```
- 예2) 원을 표현하는 객체
```javascript
var circle = {
	center : { x : 1.0, y : 2.0 }, // 원의 중심을 표현하는 객체 
    radius : 2.5 // 원의 반지름
};
```

- 예3) 회원 정보를 표현하는 객체
```javascript
var person = {
   name : "이용교", 
   age : 41,
   gender : "남",
   married : true
};
```

## 메서드
프로퍼티에 저장된 값의 타임이 함수인 경우 
```javascript
var person = {
   name : "이용교", 
   age : 41,
   gender : "남",
   married : true,
 getLoginInfo : function() {}
};

person.getLoginInfo();
```

## 객체는 참조 타입
- 생성된 객체는 메모리의 영역을 차지하는 한 덩어리가 됩니다. 
- 객체 타입의 값을 변수에 대입하면 그 변수에는 객체의 참조(메모리에서의 위치 정보)가 저장됩니다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image1.png)

- 변수에 저장된 객체의 참조는 다른 원시값과 마찬가지로 다른 변수에 저장할 수 있음
```javascript
var a = card;
console.log(a.suit); // -> 하트
a.suit = "스페이드";
console.log(a.suit); // -> 스페이드
console.log(card.suit); // 스페이드

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/2.%EC%9B%B9%ED%91%9C%EC%A4%80(48%EC%8B%9C%EA%B0%84)/5%EC%9D%BC%EC%B0%A8(3h)%20-%20Javascript(%EA%B0%9D%EC%B2%B4%20%EB%A6%AC%ED%84%B0%EB%9F%B4%2C%20%ED%95%A8%EC%88%98%2C%EB%A9%94%EC%84%9C%EB%93%9C%2C%20%EC%83%9D%EC%84%B1%EC%9E%90)/images/image2.png)
```


* * *
# 함수
- 일련의 처리를 하나로 모아 언제든 호출할 수 있도록 만들어 놓은것
- 수학 함수와 비슷
- 함수의 입력 값을 인수, 함수의 출력 값을 반환값
```javascript 
function 함수명 (매개변수) {   
	처리 로직 
 
   return 출력 반환값
}
```
## 함수 선언문으로 함수 정의하기
- 함수는 function 키워드를 사용해서 정의
```javascript
function square(x) {    
	var result = x * x; 
	
	return result; 
}  
``` 
- square - 함수 이름
- x - 매개변수
- var result = x \* x - 처리 로직 
- return result - 처리 후 반환값 
- 참조) 함수명 캐멀 표기법

## 함수 호출
함수를 호출하려면 함수 이름 뒤에 소괄호 인수를 묶어 입력
```javascript
square(3); // 9
```
## 매개변수

함수는 매개변수를 여러 개 받을 수 있음
- 매개변수가 여러 개라면 매개변수와 매개변수를 쉼표(,)로 구분
```javascript
function add(a, b) {   
	var c = a + b;
	return c;
}
```
- 인수를 받지 않는 함수도 정의할 수 있음
```javascript
function bark() {    
	console.log(”멍멍”); 
};

bark(); // 멍멍 
console.log(bark()); // undefined - 반환값이 없으므로
```

## 함수의 실행흐름
- 호출된 코드에 있는 인수가 함수 정의문에 대입된다. 
- 함수 정의문의 중괄호 안에 작성된 플그램이 순차적으로 실행된다.
- return 문이 실행되면 호출된 코드로 돌아간다. return 문의 값은 함수의 반환값이 된다.
- return 문이 실행되지 않은 상태로 마지막 문장이 실행되면, 호출한 코드로 돌아간 후에 
- undefined가 함수의 반환값이 된다.

## 함수 선언문의 끌어올림
- 자바스크립트 엔진은 변수 선언문과 마찬가지로 함수 선언문을 프로그램의 첫머리로 끌어올림
- 따라서 함수 선언문은 프로그램 어떤 위치에서도 작성할 수 있다.

```javascript
console.log(square(5)); // 25 
function square(x) {  return x * x; }
```

## 값으로서의 함수
- 자바스크립트에서는 함수가 객체입니다. 
- 함수를 선언하면 내부적으로는 그 함수 이름을 변수 이름으로 한 변수와 함수 객체가 만들어지고, 그 변수에 함수 객체의 참조가 저장됩니다.
- 이 변수 값을 다른 변수에 할당하면 그 변수 이름으로 함수를 실행할 수 있습니다.
```javascript
var sq = square;
console.log(sq(5)); // 25 
```
- 함수를 다른 함수의 인수로 넘길 수도 있습니다.
```javascript
function callback(scrollTop) {
    console.log(`이벤트 콜백 부분 - 인수${scrollTop}`);

    return scrollTop;
}

function goTop(callback) {
   var scrollTop = window.pageYOffset;
   
   return callback(scrollTop);
}
```

* * * 
# 생성자

