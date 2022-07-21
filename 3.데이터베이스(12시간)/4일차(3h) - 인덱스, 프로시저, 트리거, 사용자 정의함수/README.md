# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1tVqDYixHNRwQiN6S_QZPRQl9m8HUqNSx?usp=sharing)

## 인덱스
- 인덱스(index - 색인)란 자료를 쉽고 빠르게 찾을 수 있도록 만든 데이터 구조이다.
- 도서관에서 책을 찾을 때 서지목록을 보고 책의 위치를 찾는 것처럼 인덱스도 같은 역할을 한다.
- 데이터베이스에서 인덱스란 원하는 데이터를 빨리 찾기 위해 투플의 키 값에 대한 물리적 위치를 기록해둔 자료구조이다.

### 인덱스의 특징 정리
- 인덱스는 테이블에서 한 개 이상의 속성을 이용하여 생성한다.
- 빠른 검색과 함께 효율적인 레코드 접근이 가능하다.
- 순서대로 정렬된 속성과 데이터의 위치만 보유하므로 테이블보다 작은 공간을 차지한다.
- 저장된 값들은 데이블의 부분집합이 된다.
- 일반적으로 B-tree 형태의 구조를 가진다.
- 데이터의 수정, 삭제 등의 변경이 발생하면 인덱스의 재구성이 필요하다.

#### MySQL 인덱스의 종류 

|인덱스 명칭|설명 / 생성 예|
|----|----------|
|클러스터<br>인덱스|기본적인 인덱스로 테이블 생성 시 기본키를 지정하면 기본 키에 대하여 클러스터 인덱스를 생성한다.<br>기본키를 지정하지 않으면 먼저 나오는 UNIQUE 속성에 대하여 클러스터 인덱스를 생성한다.<br>기본키나 UNIQUE 속성이 없는 테이블은 MySQL이 자체 생성한 행번호(Row ID)를 이용하여 클러스터 인덱스를 생성한다.|
|보조<br>인덱스|클러스터 인덱스가 아닌 모든 인덱스는 보조 인덱스이며, 보조 인덱스의 각 레코드는 보조 인덱스 속성과 기본키 속성 값을 갖고 있다.<br>보조 인덱스를 검색하여 기본키 속성 값을 찾은 다음 클러스터 인덱스로 가서 해당 레코드를 찾는다.|


### 인덱스의 생성
- 인덱스는 데이터 검색을 빨리하기 위해 사용한다. 하지만 인덱스를 생성했다고 해서 데이터 검색이 무조건 빨라지는 것은 아니다. 
- 데이터 양이 별로 없거나 값이 몇 종류 안되어 선택도가 높을 경우 인덱스가 없는 게 더 빠를 수 있다. 
- 여기서 선택도(selectivity)란 '1/서로다른 값의 개수'을 말하는 것으로, 예를 들어 100개의 행을 가진 테이블에 값이 (남, 여) 두 가지라면 선택도가 높다고 할 수 있다.
- 이와 같이 의미 없는 인덱스를 생성하면 검색이 더 느려지고 저장 공간만 낭비하게 된다. 
- 따라서 인덱스 생성에 앞서 다음의 고려사항을 충분히 살펴봐야 한다.
	- 인덱스는 WHERE 절에 자주 사용되는 속성이어야 한다.
	- 인덱스는 조인에 자주 사용되는 속성이어야 한다. 
	- 단일 테이블에 인덱스가 많으면 속도가 느려질 수 있다(테이블 당 4~5개 정도 권장).
	- 속성이 가공되는 경우 사용하지 않는다.
	- 속성의 선택도가 낮을 때 유리하다(속성의 모든 값이 다른 경우).
	
- 인덱스를 생성하는 문법은 다음과 같다.
```
CREATE [UNIQUE] INDEX [인덱스이름]
ON 테이블이름 (컬럼 [ASC | DESC] [{, 컬럼 [ASC | DESC]} ...])[;]
```

- Book 테이블의 bookname 열을 대상으로 인덱스 ix_Book을 생성하시오
```
CREATE INDEX ix_Book ON Book(bookname);
```	

- Book 테이블의 publisher, price 열을 대상으로 인덱스 ix_Book2를 생성하시오.
```
CREATE INDEX ix_Book2 ON Book(publisher, price);
```

- 생성된 인덱스는 SHOW INDEX 명령어로 확인할 수 있다.
```
SHOW INDEX FROM Book;
```

- MySQL이 생성된 인덱스를 활용하여 SQL문을 처리하였는지 확인하려면 MySQL Workbench에서 \[Query\] -> \[Explain Current Statement\]를 누르면 된다.
- 실행계획이 나타나면서 인덱스를 활용하여 결과를 출력하는 과정을 볼 수 있다. 다음 SQL문을 이용하여 테스트해보자.
```
SELECT *
FROM Book
WHERE publisher='대한미디어' AND price >= 30000;
```

### 인덱스 재구성과 삭제
- 인덱스 재구성은 ANALYZE TABLE 명령을 사용하여 수행한다.
- B-tree 인덱스는 데이터의 수정, 삭제, 삽입이 잦으면 노드의 갱신이 주기적으로 일어나 단편화(fragmentation)현상이 나나난다. 
- 단편화란 삭제된 레코드의 인덱스 값 자리가 비게 되는 상태를 말하는데, 이는 검색시 성능저하로 이어진다. 
- 이럴 경우 ANALYZE 문법을 통해 인덱스를 다시 생성해준다.

```
ANALYZE TABLE 테이블이름;
```
- Book 테이블의 인덱스를 최적화하시오.
```
ANALYZE TABLE Book;
```

- 하나의 테이블에 인덱스가 많으면 데이터베이스 성능에 좋지 않은 영향을 미친다. 그러므로 사용하지 않는 인덱스는 삭제해야 한다.
- 인덱스의 삭제는 DROP INDEX 명령을 사용한다.

- 인덱스 ix_Book을 삭제하시오.
```
DROP INDEX ix_Book ON Book;
```

* * * 
## 프로시저 - 저장프로그램
- 저장 프로그램(Stored Program)은 데이터베이스 응용 프로그램을 작성하는 데 사용하는 MySQL의 SQL 전용 언어이다.
- SQL 전용 언어는 SQL문에 변수, 제어, 입출력 등의 프로그래밍 기능을 추가하여 SQL만으로 처리하기 어려운 문제를 해결한다.
- 저장 프로그램은 로직을 프로시저(procedure)로 구현하여 객체 형태로 사용한다. 
- 저장 프로그램은 일반 프로그래밍 언어에서 사용하는 함수와 비슷한 개념으로, 작업 순서가 정해진 독립된 프로그램 수행 단위를 말하며 정의된 다음 MySQL(DBMS)에 저장되므로 저장 프로그램이라고 한다.
- 저장 프로그램은 저장루틴(routine), 트리거(trigger), 이벤트(event)로 구성되며, 저장루틴은 프로시저(procedure)와 함수(function)로 나눈다.

> 트리거란 데이터의 변경(INSERT, DELETE, UPDATE)문이 실행될 때 자동으로 같이 실행되는 프로시저를 말한다.

- 프로시저를 정의하려면 CREATE PROCEDURE 문을 사용한다. 정의 방법은 다음과 같다.
	 - 프로시저는 선언부와 실행부(BEGIN-END)로 구성된다. 선언부에서는 변수와 매개변수를 선언하고 실행부에서는 프로그램 로직을 구성한다.
	 - 배개변수(parameter)는 저장 프로시저가 호출될 때 그 프로시저에 전달되는 값이다.
	 - 변수(variable)는 저장 프로시저나 트리거 내에서 사용되는 값이다.
	 - 소스코드에 대한 설명문은 /\*와 \*/ 사이에 기술한다. 설명문이 한 줄이면 이중 대시(--) 기호 다음에 기술해도 된다.
	 
```
delimiter //
CREATE PROCEDURE dorepeat(p1 INT)
BEGIN 
SET @x = 0;
REPEAT SET @x = @x + 1; UNTIL @x > p1 END REPEAT;
END
//
delimiter ;

CALL dorepeat(1000);
SELECT @x;
```

### 삽입 작업을 하는 프로시저
- Book  테이블에 한 개의 투플을 삽입하는 프로시저

```
use madang;
delimiter //
CREATE PROCEDURE InsertBook(
	IN myBookID INTEGER,
	IN myBookName VARCHAR(40),
	IN myPublisher VARCHAR(40),
	IN myPrice INTEGER)
BEGIN 
	INSERT INTO Book(bookid, bookname, publisher, price)
	VALUES(myBookID, myBookName, myPublisher, myPrice);
END;
//
delimiter ;
```
- 프로시저 정의문은 CREATE PROCEDURE-BEGIN-END 형식이다. 프로시저 이름 뒤에 인자를 정의하였고, BEGIN-END에는 INSERT 문을 작성하였다. 
-  프로시저 종료가 \/\/에서 끝남을 알리는 delimiter이다.
- IN은 입력 인자, OUT은 출력 인자
- 프로시저를 작성한 후 \[실행\]을 클릭하면 정의된 프로시저 InsertBook이 개체 탐색기 madang 데이터베이스 'Stored Procedures'항목에 생성된다.
- 정의된 프로시저는 CALL문으로 호출한다.

### 제어문을 사용하는 프로시저

#### 저장 프로그램 제어문

|구문|의미|문법|
|----|-------|-----|
|DELIMITER|구문 종료 기호 설정|DELIMITER \[기호\]|
|BEGIN-END|프로그램 문을 블록화시킴<br>중첩 가능|BEGIN<br>\[SQL 문\]<br>END|
|IF-ELSE|조건의 검사 결과에 따라 문장을 선택적으로 수행|IF \<조건\> THEN \[SQL 문\]<br>\[ELSE \[SQL 문\]\]<br>END IF;|
|LOOP|LEAVE 문을 만나기 전까지 LOOP을 반복|\[label:\] LOOP<br>\[SQL 문\]\[LEAVE \[label\]\]<br>END LOOP|
|WHILE|조건이 참일 경우 WHILE 문의 블록을 실행|WHILE \<조건\> DO<br>\[SQL 문\| BREAK \| CONTINUE \]<br>END WHILE|
|REPEAT|조건이 참일 경우 REPEAT 문의 블록을 실행|\[label:\] REPEAT<br>\[SQL 문 \| BREAK \| CONTINUE \]<br>UNTIL \<조건\><br>END REPEAT \[label:\]|
|RETURN|프로시저를 종료<br>상태값 반환 가능|RETURN \[\<식\>\]|

- 동일한 도서가 있는지 점검한 후 삽입하는 프로시저
```
use madang;
delimiter //
CREATE PROCEDURE BookInsertOrUpdate(
	myBookID INTEGER,
	myBookName VARCHAR(40),
	myPublisher VARCHAR(40),
	myPrice INTEGER)
BEGIN
	DECLARE mycount INTEGER
	SELECT COUNT(*) INTO mycount FROM Book
	  WHERE bookname LIKE myBookName;
	IF mycount != 0 THEN
	 SET SQL_SAFE_UPDATES=0; /* DELETE, UPDATE 연산에 필요한 설명문 */
	 UPDATE Book SET price = myPrice
	   WHERE bookname LIKE myBookName;
    ELSE 
	  INSERT INTO Book(bookid, bookname, publisher, price)
	    VALUES(myBookID, myBookName, myPublisher, myPrice);
	END IF;
END;
//
delimiter ;
```
-  실행방법
```
CALL BookInsertOrUpdate(15, '스포츠 즐거움', '마당과학서적', 25000);
SELECT * FROM Book; -- 15번 투플 삽입 결과 확인

CALL BookInsertOrUpdate(15, '스포츠 즐거움', '마당과학서적', 20000);
SELECT * FROM Book; -- 15번 투플 가격 변경 확인
```

- IN/OUT을 생략하면 기본값은 IN으로 설정된다.


### 결과를 반환하는 프로시저
- 반환하는 방법은 프로시저를 선언할 떄 인자의 타입을 OUT으로 설정한 후, 이 인자 변수에 값을 주면 된다.
- Book 테이블에 저장된 도서의 평균가격을 반환하는 프로시저
```
delimiter //
CREATE PROCEDURE AveragePrice(
	OUT AverageVal INTEGER)
BEGIN
	SELECT AVG(price) INTO AverageVal
	FROM Book WHERE price IS NOT NULL;
END;
//
delimiter ;
```
- 실행방법
```
CALL AveragePrice(@myValue);
SELECT @myValue;
```

### 커서를 사용하는 프로시저
- SQL 문의 실행 결과가 다중행 또는 다중열일 경우 프로그램에서는 한 행씩 처리한다. 
- 커서(cursor)는 실행 결과 테이블을 한 번에 한 행씩 처리하기 위하여 테이블의 행을 순서대로 가리키는 데 사용한다.
- 커서에 관련된 키워드는 CURSOR, OPEN, FETCH, CLOSE등이 있다.

#### 커서와 관련된 키워드

|키워드|역할|
|-------|-----|
|CURSOR \<cursor 이름\> IS \<커서 정의\><br>DECLARE \<cursor 이름\> CURSOR FOR|커서를 생성|
|OPEN \<cursor 이름\>|커서의 사용을 시작|
|FETCH \<cursor 이름\> INTO \<변수\>|행 데이터를 가져옴|
|CLOSE \<cursor 이름\>|커서의 사용을 끝냄|

- Orders 테이블의 판매 도서에 대한 이익을 계산하는 프로시저

```
delimiter //
CREATE PROCEDURE Interest()
BEGIN
	DECLARE myInterest INTEGER DEFAULT 0.0;
	DECLARE Price INTEGER;
	DECLARE endOfRow BOOLEAN DEFAULT FALSE;
	DECLARE InterestCursor CURSOR FOR 
		SELECT saleprice FROM Orders;
	DECLARE CONTINUE handler
		FOR NOT FOUND SET endOfRow=TRUE;
	OPEN InterestCursor;
	cursor_loop : LOOP
		FETCH InterestCursor INTO Price;
		IF endOfRow THEN LEAVE cursor_loop;
		END IF;
		IF Price >= 30000 THEN
			SET myInterest = myInterest + Price * 0.1;
		ELSE 
			SET myInterest = myInterest + Price * 0.05;
	END LOOP cursor_loop;
	CLOSE InterestCursor;
	SELECT CONCAT(' 전체 이익 금액 = ', myInterest);
END
//
delimiter ;
```
- 실행방법
```
CALL Interest();
```

* * * 
## 트리거
- 트리거(trigger)는 데이터의 변경(INSERT, DELETE, UPDATE) 문이 실행될 때 자동으로 같이 실행되는 프로시저를 말한다.
- 보통 트리거는 데이터의 변경문이 처리되는 세 가지 시점, 즉 실행 전(BEFORE), 대신하여(INSTEAD OF), 실행 후(AFTER)에 동작한다.
- DBMS 재조사에 따라 트리거의 정의가 많이 다르다.
- 트리거는 데이터의 변경(삽입, 삭제, 수정)이 일어날 때 부수적으로 필요한 작업인 데이터의 기본값 제공, 데이터의 제약 준수, SQL 뷰의 수정, 참조무결성 작업 등을 수행한다.

- 신규 도서를 삽인한 후 자동으로 Book_log 테이블에 삽인한 내용을 기록하는 트리거
```
실습을 위해 root 계정에서 트리거 작동을 위하여 다음 문장을 실행해 준다.

SET global log_bin_trust_function_creators=ON;
```
```
 CREATE TABLE Book_log(
	bookid_l INTEGER,
	bookname_l VARCHAR(40),
	publisher_l VARCHAR(40),
	price_l INTEGER);
```
```
delimiter // 
CREATE TRIGGER AfterInsertBook 
	AFTER INSERT ON Book FOR EACH ROW
BEGIN
	INSERT INTO Book_log 
	  VALUES(new.bookid, new.bookname, new.publisher, new.price);
END;
//
delimiter ;
```
- 실행방법
```
INSERT INTO Book VALUES(14, '스포츠 과학 1', '이상미디어', 25000);
SELECT * FROM Book WHERE bookid=14;
SELECT * FROM Book_log WHERE bookid_l=14; -- 결과 확인
```

* * *
## 사용자 정의함수
- 사용자 정의 함수는 사용자가 직접 필요한 기능을 함수로 만들어 사용한다.
- 프로시저와 비슷해 보이지만 프로시저는 CALL 명령에 의해 실행되는 독립적인 프로그램이고, 사용자 정의 함수는 SELECT  문이나 프로시저 내에서 호출되어 SQL 문이나 프로시저에 그 값을 제공하는 용도로 사용된다.
- MySQL에서 작성할 수 있는 사용자 정의 함수는 단일 값을 돌려주는 스칼라 함수가 일반적이다.

- 판매된 도서에 대한 이익을 계산하는 함수
```
delimiter //
CREATE FUNCTION fnc_Interest(
	Price INTEGER) RETURNS INT
BEGIN
	DECLARE myInterest INTEGER;
	-- 가격이 30,000원 이상이면 10%, 30,000원 미만이면 5%
	IF Price >= 30000 THEN SET myInterest = Price * 0.1;
	ELSE SET myInterest = Price * 0.05;
	END IF 
	RETURN myInterest;
END;
//
delimiter ;
```

-  실행방법
```
SELECT custid, orderid, saleprice, fnc_interest(saleprice) interest FROM Orders;
```


#### 프로시저, 트리거, 사용자 정의 함수의 공통점과 차이점

|구분|프로시저|트리거|사용자 정의 함수|
|----|------|------|------|
|공통점|저장프로시저|저장프로시저|저장프로시저|
|정의방법|CREATE PROCEDURE문|CREATE TRIGGER문|CREATE FUNCTION문|
|호출방법|CALL 문으로 직접 호출|INSERT, DELETE, UPDATE 문이 실행될 떄 자동으로 실행됨|SELECT 문에 포함|
|기능의 차이|SQL 문으로 할 수 없는 복잡한 로직을 수행|기본값 제공, 데이터의 제약 준수, SQL 뷰의 수정, 참조무결성 작업등을 수행|속성 값을 가공하여 반환, SQL 문에서 직접 사용|

