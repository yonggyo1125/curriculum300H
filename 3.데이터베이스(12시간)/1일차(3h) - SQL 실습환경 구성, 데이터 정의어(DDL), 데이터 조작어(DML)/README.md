# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1tVqDYixHNRwQiN6S_QZPRQl9m8HUqNSx?usp=sharing)

## SQL 실습 환경 구성
- DBMS에게 원하는 내용을 알려주고 결과를 얻는 데 사용하는 데이터베이스 전용 언어가 SQL이다.
- SQL은 기능에 따라 데이터 정의어(DDL, Data Definition Language)와 데이터 조작어(DML, Data Manipulation Language), 데이터 제어어(DCL, Data Control Language)로 나눈다.
	- 데이터 정의어(DDL) : 테이블이나 관계의 구조를 생성하는 데 사용하며 CREATE, ALTER, DROP문 등이 있다.
	- 데이터 조작어(DML) : 테이블에 데이터를 검색, 삽입, 수정, 삭제하는 데 사용하며 SELECT, INSERT, DELETE, UPDATE 문 등이 있다. 여기서 SELECT문은 특별히 <b>질의어(query)</b>라고 부른다.
	- 데이터 제어어(DCL) : 데이터의 사용 권한을 관리하는 데 사용하며 GRANT, REVOKE 문등이 있다.


### MySQL 설치 및 Workbench 설치
- <a href='' target='_blank'>https://www.mysql.com</a>접속 -> <a href='https://www.mysql.com/downloads/' target='_blank'>DOWNLOAD<a>클릭

![mysql0](https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/images/mysql0.png)

- MySQL Community(GPL) Downloads 클릭

![mysql1](https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/images/mysql1.png)

- MySQL Installer for Windows 클릭

![mysql2](https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/images/mysql2.png)

- Windows(x86, 32-bit), MSI installer -> Download 클릭

![mysql3](https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/images/mysql3.png)

- No thanks, just start my download 클릭

![mysql4](https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/images/mysql4.png)

- 완료되면, 열기 -> 설치 시작 -> 설치 중\[Execute\]혹은 \[Next\] 버튼으로 진행
 
### 샘플 데이터 설치
- <a href='https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/sample_data/demo_madang.sql' target='_blank'>다운로드1</a>
- <a href='https://github.com/yonggyo1125/curriculum300H/blob/main/3.%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4(12%EC%8B%9C%EA%B0%84)/1%EC%9D%BC%EC%B0%A8(3h)%20-%20SQL%20%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD%20%EA%B5%AC%EC%84%B1%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A0%95%EC%9D%98%EC%96%B4(DDL)%2C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%A1%B0%EC%9E%91%EC%96%B4(DML)/sample_data/demo_madang_init.sql' target='_blank'>다운로드2</a>
 
* * * 
## 데이터베이스 기본 개념 
- 데이터베이스<br>
조직에 필요한 정보를 얻기 위해 논리적으로 연관덴 데이터를 모아 구조적으로 통합해 놓은 것 

- DBMS<br>
 DBMS란 Database Management system의 준말로 데이터를 한곳에 모은 저장소를 만들고 그 저장소에 여러 사용자가 접근하여 데이터를 저장 및 관리 등의 기능을 수행하며 공유할 수 있는 환경을 제공하는 응용 소프트웨어 프로그램이다. 공유 저장소(서버)를 구축하고 사용자들에게 접근정보를 공유하여 데이터를 처리할 수 있는 인터페이스를 제공하고 복구기능과 보안성 기능 또한 제공한다.
 
	- DBMS의 장점
		- 데이터 중복 최소화
		- 데이터 공유(일관성 유지)
		- 정합성,무결성,보안성 유지
		- 사용자 중심의 데이터 처리
		- 데이터 표준화 적용 가능
		- 데이터 접근 용이
		- 데이터 저장 공간 공유로 인한 절약

- 릴레이션<br>관계 데이터 모델의 핵심적인 개념으로 행과 열로 구성된 테이블을 말한다. 
- 릴레이션 스키마<br>관계 데이터베이스의 릴레이션이 어떻게 구성되는지 어떤 정보를 담고 있는지에 대한 기본적인 구조를 정의한다.
- 릴레이션 인스턴스<br>릴레이션 스키마에 실제로 저장되는 데이터의 집합을 말한다.
- 관계 데이터베이스 시스템<br>관계 데이터 모델을 컴퓨터 시스템이 구현한 것이다.
- 키
	- 슈퍼키 : 투플을 유일하게 식별할 수 있는 하나의 속성 혹은 속성 집합이다.
	- 후보키 : 투플을 유일하게 식별할 수 있는 하나의 속성 혹은 속성의 집합으로, 속성의 최소 집합이다.
	- 기본키 : 여러 후보키 중 하나를 선정하여 대표로 삼는 키를 말한다.
	- 대리키 : 일련번호 같은 가상의 속성을 만들어 기본키로 삼는 것을 말한다.
	- 외래키 : 다른 릴레이션의 기본키를 참조하는 속성을 말한다.
	
- 무결성 제약조건
	- 도메인 무결성 제약조건 : 모든 투플은 릴레이션 스키마에 정의된 각 속성의 도메인에 지정된 값을 가져야 한다.
	- 개체 무결성 제약조건 : 기본키는 NULL 값을 가져서는 안되며, 릴레이션 내에 오직 하나의 값만 존재해야 한다.
	- 참조 무결성 제약조건 : 자식 릴레이션의 외래키는 부모 릴레이션의 기본키의 속성의 도메인이 동일해야 하며, 자식 릴레이션의 값 변경 시 부모 릴레이션의 값 변경 시 부모 릴레이션의 값에 제약을 받는다.
	
- 참조 무결성 제약조건의 옵션
	- 참조 무결성 제약조건을 수행하기 위한 네 가지 옵션은 RESTRICTED, CASCADE, DEFAULT, NULL 등이 있다.
 
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

<pre>
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
<pre>
bookname은 NULL 값을 가질 수 없고, publisher는 같은 값이 있으면 안 된다. 
price에 값이 입력되지 않을 경우 기본값 10000을 저장한다. 
또 가격은 최소 1,000원 이상으로 한다.
</pre>
```
CREATE TABLE NewBook (
	bookname VARCHAR(20) NOT NULL, 
	publisher VARCHAR(20) UNIQUE,
	price INTEGER DEFAULT 10000 CHECK(price >= 1000),
	PRIMARY KEY (bookname, publisher)
);
```

<pre>
다음과 같은 속성을 가진 NewCustomer 테이블을 생성하시오.

- custid(고객번호) - INTEGER, 기본키
- name(이름) - VARCHAR(40)
- address(주소) - VARCHAR(40)
- phone(전화번호) - VARCHAR(30)
</pre>

```
CREATE TABLE NewCustomer (
	custid INTEGER PRIMARY KEY,
	name VARCHAR(40),
	address VARCHAR(40),
	phone VARCHAR(30)
);
```

<pre>
다음과 같은 속성을 가진 NewOrders 테이블을 생성하시오.

- orderid(주문번호) - INTEGER, 기본키
- custid(고객번호) - INTEGER, NOT NULL 제약조건, 외래키(NewCustomer.custid, 연쇄 삭제)
- bookid(도서번호) - INTEGER, NOT NULL 제약조건
- saleprice(판매가격) - INTEGER
- orderdate(판매일지) - DATE
</pre>

- CREATE 문에서 외래키를 생성해보자. NewOrders 테이블은 고객의 주문 사항을 저장하며, 속성으로 orderid, custid, bookid, saleprice, orderdate를 갖는다. 
- orderid는 주문번호를 나타내는 기본키며, custid는 NewCustomer의 custid를 참조하는 외래키다.
```
CREATE TABLE NewOrders (
	orderid INTEGER,
	custid INTEGER NOT NULL, 
	bookid INTEGER NOT NULL,
	saleprice INTEGER,
	orderdate DATE,
	PRIMARY KEY(orderid),
	FOREIGN KEY(custid) REFERENCES NewCustomer(custid) ON DELETE CASCADE
);
```
- 외래키 제약조건을 명시할 때는 주의할 점이 있다. 반드시 참조되는 테이블(부모 릴레이션)이 존재해야 하며 참조되는 테이블의 **기본키**여야 한다.
- 외래키 지정 시 ON DELETE 옵션은 참조되는 테이블의 투플이 삭제될 때 취할 수 있는 동작을 지정한다. 
- 위 CREATE 문은 ON DELETE CASCADE 옵션으로 외래키 custid를 설정하였다. 이 경우 참조되는 NewCustomer 테이블의 투플(NewCustomer.custid=3일 경우라고 가정)이 삭제되면 참조하는 NewOrders 테이블의 해당 투플(NewOrders.custid=3)이 연쇄 삭제(**CASCADE**)된다.
- SET NULL 옵션은 NULL 값으로 바꾸며,
- NO ACTION은 옵션의 기본(default) 값으로 어떠한 동작도 취하지 않는다.

#### 데이터 타입 종류
|데이터 타입|설명|ANSI SQL 표준타입|
|----|---------|-----|
|INTEGER<br>INT|4바이트 정수형을 지정한다.|INTEGER<br>INT<br>SMALLINT|
|NUMERIC(m,d)<br>DECIMAL(m,d)|전체 자릿수 m, 소수점이하 자리수 d를 가진 숫자형을 지정한다.|DECIMAL(p, s)<br>NUMERIC(p, s)|
|CHAR(n)|문자형 고정길이, 문자를 저장하고 저장하고 남은 공간은 공백으로 채운다.|CHARACTER(n)<br>CHAR(n)|
|VARCHAR(n)|문자형 가변길이를 저장한다.|CHARACTER VARING(n)<br>CHAR VARYING(n)|
|DATE|날짜형, 연도, 월, 날, 시간을 저장한다.||

> 데이터 타입의 상세내용은 [MySQL의 Reference Manual](https://dev.mysql.com/doc/refman/8.0/en/data-types.html) 을 참조한다.

### ALTER 문
- ALTER문은 생성된 테이블의 속성과 속성에 관한 제약을 변경하며, 기본키 및 외래키를 변경한다. 
- ALTER문의 문법은 다음과 같다.
```
ALTER TABLE 테이블 이름
	[ADD 속성이름 데이터타입]
	[DROP COLUMN 속성이름]
	[ALTER COLUMN 속성이름 데이터타입]
	[ALTER COLUMN 속성이름 [NULL | NOT NULL]]
	[ADD PRIMARY KEY(속성이름)]
	[[ADD | DROP] 제약이름]
```
- ALTER문에서 ADD, DROP은 속성을 추가하거나 제거할 때 사용하고 MODIFY는 속성을 변경할 때 사용한다. 
- 그리고 ADD \<제약이름\>, DROP \<제약이름\>은 제약사항을 추가하거나 삭제할 때 사용한다.

- 다음은 앞에서 실습한 NewBook 테이블을 생성하는 CREATE 문이다.
```
CREATE TABLE NewBook (
	bookid INTEGER,
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER
);
```

- NewBook 테이블에 VARCHAR(13)의 자료형을 가진 isbn을 추가하시오.

```
ALTER TABLE NewBook ADD isbn VARCHAR(13);
```

- NewBook 테이블에 isbn 속성의 데이터 타입을 INTEGER형으로 변경하시오.
```
ALTER TABLE NewBook MODIFY isbn INTEGER;
```

- NewBook 테이블의 isbn 속성을 삭제하시오.
```
ALERT TABLE NewBook DROP COLUMN isbn;
```

- NewBook 테이블의 bookid 속성에 NOT NULL 제약조건을 적용하시오.
```
ALTER TABLE NewBook MODIFY bookid INTEGER NOT NULL;
```

- NewBook 테이블의 bookid 속성을 기본키로 변경하시오.
```
ALTER TABLE NewBook ADD PRIMARY KEY(bookid);
```
> 기본키는 NOT NULL 속성만 가능하다.


### DROP 문
- DROP문은 테이블을 삭제하는 명령이다. 
- DROP문은 테이블의 구조와 데이터를 모두 삭제하므로 사용에 주의해야 한다. 
- 데이터만 삭제하려면 DELETE 문을 사용한다. 

```
DROP TABLE 테이블이름
```

- NewBook 테이블을 삭제하시오.
```
DROP TABLE NewBook;
```

- NewCustomer 테이블을 삭제하시오. 만약 삭제가 거절된다면 원인을 파악하고 관련된 테이블을 같이 삭제하시오(NewOrders 테이블이 NewCustomer를 참고하고 있는 상태이다).

```
DROP TABLE NewCustomer;
```
- 삭제하려는 테이블의 기본키를 다른 테이블에서 참조하고 있다면 삭제가 거절된다. 
- NewCustomer 테이블을 삭제하기 위해서는 참조하고 있는 테이블 NewOrders 테이블부터 삭제해야 한다.

```
DROP TABLE NewOrders;
DROP TABLE NewCustomer;
```

* * * 
## 데이터 조작어(DML) - 검색
- SQL의 SELECT문은 데이터를 검색하는 기본 문장으로 특별히 <b>질의어(query)</b>라고 부른다.
- SELECT 문은 검색한 결과를 테이블 형태로 출력한다.
- 예) 10,000원 이상인 도서의 이름과 출산사를 보이시오
```
SELECT bookname, publisher   // 속성 이름
FROM Book   // 테이블 이름 
WHERE price >= 10000;  // 검색 조건
```

### SELECT문의 기본 문법
```
SELECT [ALL | DISTINCT] 속성이름(들)
FROM 테이블이름(들)
[WHERE 검색조건(들)]
[GROUP BY 속성이름]
[HAVING  검색조건(들)]
[ORDER BY 속성이름 [ASC | DESC]]
```
- \[\] : 대괄호 안의  SQL 예약어들은 선택적으로 사용한다.
- | : 선택 가능한 문법들 중 한 개를 사용할 수 있다.

### SELECT문의 자세한 문법
```
SELECT 
			[ALL | DISTINCT]
			[테이블이름.]{ * | 속성이름 [[AS] 속성이름 별칭]}
[FROM
			{테이블이름 [AS 테이블이름별칭]}
			[INNER JOIN | LEFT [OUTER] JOIN | RIGHT [OUTER] JOIN {테이블이름 [ON 검색조건]}
			| FULL [OUTER] JOIN {테이블이름}]] 
[WHERE 검색조건(들)]
[GROUP BY {속성이름, [..., n]}]
[HAVING 검색조건(들)]
[질의 UNION 질의 | 질의 UNION ALL 질의]
[ORDER BY {속성이름 [ASC | DESC], [..., n]}]
```
- \[\] : 대괄호 안의 SQL의 예약어들은 선택적으로 사용한다.
- {} : 중괄호 안의 SQL 예약어들은 필수적으로 사용한다.
- | : 선택 가능한 문법들 중 한 개를 사용할 수 있다.

### SELECT문 

- 모든 도서의 이름과 가격을 검색하시오.
```
SELECT bookname, price
FROM Book;
```

- SQL은 세미콜론(;)과 함께 끝난다. 세미콜론은 SQL-92 표준에서 정한 사항이지만 세미콜론을 생략해도 된다. 그러나 SQL문을 작성할 때는 세미콜론과 함께 마치는 습관을 기르도록 하자.
- 대소문자의 구분은 없지만 SQL 예약어는 대문자로, 테이블이나 속성이름은 소문자로 적어주면 읽기 쉽다.
- SELECT 절에서 열 순서는 절과 테이블의 열 순서를 결정한다. 만약 SELECT 절에 bookname과 price를 바꾸면 결과 테이블의 순서가 바뀐다.
```
SELECT price, bookname
FROM Book;
```

- 모든 도서의 도서번호, 도서이름, 출판사, 가격을 검색하시오.
```
SELECT bookid, bookname, publisher, price
FROM Book;
```

> SELECT 절에 '\*'(asterisk라고 한다)는 모든 열을 나타낸다. '\*'를 써주면 열의 이름을 쓰지 않아도 된다.
```
SELECT * 
FROM Book;
```

- 도서 테이블에 있는 모든 출판사를 검색하시오.
```
SELECT publisher 
FROM Book;
```

- 중복을 제거하고 싶으면 다음과 같이 DISTINCT라는 키워드를 사용한다.
```
SELECT DISTINCT publisher 
FROM Book;
```