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


- 변수에 저장된 객체의 참조는 다른 원시값과 마찬가지로 다른 변수에 저장할 수 있음
```javascript
var a = card;
console.log(a.suit); // -> 하트
a.suit = "스페이드";
console.log(a.suit); // -> 스페이드
console.log(card.suit); // 스페이드
```


* * *
# 함수, 메서드

* * * 
# 생성자

