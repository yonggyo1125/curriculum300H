## SQL 실습 환경 구성
- DBMS에게 원하는 내용을 알려주고 결과를 얻는 데 사용하는 데이터베이스 전용 언어가 SQL이다.
- SQL은 기능에 따라 데이터 정의어(DDL, Data Definition Language)와 데이터 조작어(DML, Data Manipulation Language), 데이터 제어어(DCL, Data Control Language)로 나눈다.
	- 데이터 정의어(DDL) : 테이블이나 관계의 구조를 생성하는 데 사용하며 CREATE, ALTER, DROP문 등이 있다.
	- 데이터 조작어(DML) : 테이블에 데이터를 검색, 삽입, 수정, 삭제하는 데 사용하며 SELECT, INSERT, DELETE, UPDATE 문 등이 있다. 여기서 SELECT문은 특별히 <b>질의어(query)</b>라고 부른다.
	- 데이터 제어어(DCL) : 데이터의 사용 권한을 관리하는 데 사용하며 GRANT, REVOKE 문등이 있다.


### MySQL 설치 및 Workbench 설치
- <a href='' target='_blank'>https://www.mysql.com</a>접속 -> <a href='https://www.mysql.com/downloads/' target='_blank'>DOWNLOAD<a>클릭

![mysql0](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/images/mysql0.png)

- MySQL Community(GPL) Downloads 클릭

![mysql1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/images/mysql1.png)

- MySQL Installer for Windows 클릭

![mysql2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/images/mysql2.png)

- Windows(x86, 32-bit), MSI installer -> Download 클릭

![mysql3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/images/mysql3.png)

- No thanks, just start my download 클릭

![mysql4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/images/mysql4.png)

- 완료되면, 열기 -> 설치 시작 -> 설치 중\[Execute\]혹은 \[Next\] 버튼으로 진행
 
### 샘플 데이터 설치
- <a href='https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/sample_data/demo_madang.sql' target='_blank'>다운로드1</a>
- <a href='https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)/sample_data/demo_madang_init.sql' target='_blank'>다운로드2</a>
 
 * * * 
## 데이터 정의어(DDL)
- 데이터를 저장하려면 먼저 **데이터를 저장할 테이블의 구조**를 만들어야 한다.
- SQL의 데이터 정의어(DDL, Data Definition Language)는 바로 이 구조를 만드는 명령이다.
- 데이터 정의어는 테이블의 구조를 만드는 CREATE 문, 구조를 변경하는 ALTER문, 구조를 삭제하는 DROP문이 있다.

### CREATE 문 
- CREATE 문은 테이블을 구성하고, 속성과 속성에 관련 제약을 정의하며, 기본 키 및 외래키를 정의하는 명령이다.
```
CREATE TABLE 테이블이름(
	{속성이름 데이터 타입 [NULL | NOT NULL | UNIQUE | DEFAULT 기본 값 | CHECK 체크 조건]}
	[PRIMARY KEY 속성이름(들)]
	[FOREIGN KEY 속성이름 REFERENCES 테이블이름(속성이름)]
	[ON DELETE {CASCADE | SET NULL}]
)
```
- 문법에서 대문자은 키워드, {}안의 내용은 반복 가능, \[\]은 선택적으로 사용, |는 1개를 선택, <>은 해당되는 문법 사항이 있음을 나타낸다.
- 편의상 콤마(,) 기호는 생략하였다. 
- NOT NULL은 NULL 값을 허용하지 않는 제약
- UNIQUE는 유일한 값에 대한 제약
- DEFAULT는 기본값 설정
- CHECK는 값에 대한 조건을 부여할 때 사용한다.
- PRIMARY KEY는 기본키를 정할 때, 
- FOREIGN KEY는 외래키를 지정할 때,
- ON DELETE는 투플의 삭제 시 외래키 속성에 대한 동작을 나타낸다.
- ON DELETE의 옵션으로는 CASCADE, SET NULL이 있으며 명시하지 않으면 RESTRICT(NO ACTION)이다.

<pre style='padding: 10px; background-color: skyblue;'>
다음과 같은 속성을 가진 NewBook 테이블을 생성하시오. 정수형은 INTEGER를 사용하며 문자형은 가변형 문자타입인 VARCHAR를 사용한다.

- bookid(도서번호) - INTEGER
- bookname(도서이름) - VARCHAR(20)
- publisher(출판사) - VARCHAR(20)
- price(가격) - INTEGER
</pre>

```
CREATE TABLE NewBook (
	bookid INTEGER, 
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER
);
```
>문자형 데이터 타입 CHAR, VARCHAR<br>CHAR(n)은 n바이트를 가진 문자형 타입이다. 저장되는 문자의 길이가 n보다 작으면 나머지는 공백으로 채워서 n바이트를 만들어 저장한다. VARCHAR(n) 타입은 마찬가지로 n바이트를 가진 문자형 타입이지만 저장되는 문자의 길이만큼만 기억장소를 차지하는 가변형이다.<br><br>문자형 데이터를 사용할 때 주의할 CHAR(n)에 저장된 값과 VARCHAR(n)에 저장된 값이 비록 같을지라고 CHAR(n)은 공백을 채운 문자열이기 때문에 동등 비교 시 실패할 수 있다.

- 작성한 NewBook 테이블에는 아무 제약사항이 없다. 만약 기본키를 지정하고 싶다면 다음과 같이 생성한다. 키본키 속성에서 괄호는 필수다.
```
CREATE TABLE NewBook(
	bookid INTEGER,
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER,
	PRIMARY KEY (bookid)
);
```

- 위 CREATE 문은 다음과 같이 써도 같은 의미가 된다.
```
CREATE TABLE NewBook(
	bookid INTEGER PRIMARY KEY,
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER
);
```

- 만약 bookid 속성이 없어서 두 개의 속성 bookname, publisher가 기본키가 된다면 다음과 같이 괄호를 사용하여 복합키를 지정한다.
```
CREATE TABLE NewBook (
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER,
	PRIMARY KEY (bookname, publisher)
);
```

- NewBook 테이블의  CREATE 문에 좀 더 복잡한 제약사항을 추가하면 다음과 같다.
```
bookname은 NULL 값을 가질 수 없고, publisher는 같은 값이 있으면 안 된다. price에 값이 입력되지 않을 경우 기본값 10000을 저장한다. 또 가격은 최소 1,000원 이상으로 한다.
```
```
CREATE TABLE NewBook (
	bookname VARCHAR(20) NOT NULL, 
	publisher VARCHAR(20) UNIQUE,
	price INTEGER DEFAULT 10000 CHECK(price >= 1000),
	PRIMARY KEY (bookname, publisher)
);
```


### ALTER 문


### DROP 문

* * * 
## 데이터 조작어(DML)

