# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1tVqDYixHNRwQiN6S_QZPRQl9m8HUqNSx?usp=sharing)

## 내장함수
- SQL에서도 함수라는 개념을 사용한다.
- SQL의 함수는 크게 두가지로 나눈다. 
	- DBMS가 제공하는 <b>내장함수(built-in function)</b>
	- 사용자가 필요에 따라 직접 만드는 <b>사용자 정의 함수(user-defined function)</b>
	
### SQL 내장 함수
- SQL 내장 함수는 상수나 속성 이름을 입력 값으로 받아 단일 값을 결과로 반환한다. 
- 모든 내장 함수는 최초에 선언될 떄 유효한 입력 값을 받아야 한다.  예를 들면 수학 함수의 입력 값은 정수 또는 실수여야 한다.
- 만약 선언에 위배된 값이 입력되면 질의는 실행을 중지하고 에러 메시지를 출력한다. 
- SQL 내장 함수는 SELECT 절과 WHERE 절, UPDATE 절등에서 모두 사용 가능하다.

### 숫자 함수

#### 숫자 함수의 종류
|함수|설명|
|----|------|
|ABS(숫자)|숫자의 절대값을 계산 예) ABS(-4.5) -> 4.5|
|CEIL(숫자)|숫자보다 크거나 같은 최소의 정수 예) CEIL(4.1) -> 5|
|FLOOR(숫자)|숫자보다 작거나 같은 최소의 정수 예) FLOOR(4.1) -> 4|
|ROUND(숫자, m)|숫자의 반올림, m은 반올림 기준 자릿수 예) ROUND(5.36, 1) -> 5.40|
|LOG(n, 숫자)|숫자의 자연로그 값을 반환 예) LOG(10) -> 2.30259|
|POWER(숫자, n)|숫자의 n제곱 값을 계산 예) POWER(2, 3) -> 8|
|SQRT(숫자)|숫자의 제곱근 값을 계산(숫자은 양수) 예) SQRT(9.0) -> 3.0|
|SIGN(숫자)|숫자가 음수면 -1, 0이면 0, 양수면 1 예) SIGN(3.45) -> 1|

#### ABS 함수
ABS()는 절댓값을 구하는 함수이다.
- -7과 +78의 절댓값을 구하시오.
```
SELECT ABS(-78), ABS(+78);
```

#### ROUND 함수
ROUND()는 반올림한 값을 구하는 함수이다.
- 4.875를 소수 첫째 자리까지 반올림한 값을 구하시오.
```
SELECT ROUND(4.875, 1);
```

#### 숫자함수의 연산
- 숫자 함수는 입력 값으로 직접 숫자를 입력할 수도 있지만 열 이름을 사용할 수도 있다.
- 또한 여러 함수를 복합적으로 사용할 수도 있다.
- 고객별 평균 주문금액을 백원 단위로 반올림한 값을 구하시오.

```
SELECT custid '고객번호', ROUND(SUM(saleprice)/COUNT(*), -2) '평균금액'
FROM Orders
GROUP BY custid;
```

### 문자함수
<table>
	<thead>
	<tr>
		<th>반환 구분</th>
		<th>함수</th>
		<th>설명</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td rowspan='8'>
			문자값<br>반환 함수
			<br><br>
			s : 문자열<br>
			c : 문자<br>
			n : 정수<br>
			k : 정수
		</td>
		<td>CONCAT(s1, s2)</td>
		<td>
			두 문자열을 연결<br>
			예) CONCAT('마당', ' 서점') -> '마당 서점'
		</td>
	</tr>
	<tr>
		<td>LOWER(s)</td>
		<td>
			대상 문자열을 모두 소문자로 변환<br>
			예) LOWER("MR SCOTT") -> "mr scott"
		</td>
	</tr>
	<tr>
		<td>LAPD(s,n,c)</td>
		<td>
			대상 문자열의 왼쪽부터 지정한 자리수까지 지정한 문자로 채움<br>
			예) LPAD('page 1', 10, '*') -> '****Page 1"
		</td>
	</tr>
	<tr>
		<td>REPLACE(s1, s2, s3)</td>
		<td>
			대상 문자열의 지정한 문자를 원하는 문자로 변경<br>
			예) REPLACE('AbC', '5, '*') -> 'AbC**'
		</td>
	</tr>
	<tr>
		<td>RPAD(s,n,c)</td>
		<td>
			대상 문자열의 오른쪽부터 지정한 자리수까지 지정한 문자로 채움<br>
			예) RPAD('AbC', 5, '*') -> 'AbC**'
		</td>
	</tr>
	<tr>
		<td>SUBSTR(s,n,k)</td>
		<td>
			대상 문자열의 지정된 자리에서부터 지정된 길이만큼 잘라서 반환<br>
			예) SUBSTR('ABCDEFG', 3, 4) -> 'CDEF'
		</td>
	</tr>
	<tr>
		<td>TRIM(c FROM s)</td>
		<td>
			대상 문자열의 양쪽에서 지정된 문자를 삭제<br>(문자열만 넣으면 기본값으로 공백제거)<br>
			예) TRIM('=' FROM '==BROIWNING==') -> 'BROWNING'
		</td>
	</tr>
	<tr>
		<td>UPPER(s)</td>
		<td>
			대상 문자열을 모두 대문자로 변환<br>
			예) UPPER('mr scott') -> MR SCROTT'
		</td>
	</tr>
	<tr>
		<td rowspan='3'>숫자값<br>반환 함수</td>
		<td>ASCII(c)</td>
		<td>
			대상 알파벳 문자의 아스키 코드 값을 반환<br>
			예) ASCII('D') -> 68
		</td>
	</tr>
	<tr>
		<td>LENGTH(s)</td>
		<td>
			대상 문자열의 Byte 반환, 알파벳 1byte,  한글 3byte(UTF8)<br>
			LENGTH('CANDIDE') -> 7
		</td>
	</tr>
	<tr>
		<td>CHAR_LENGTH(s)</td>
		<td>
			문자열의 문자 수를 반환<br>
			예) CHAR_LENGTH('데이터') -> 3
		</td>
	</tr>
	</tbody>
</table>

> 한 문자가 차지하는 바이트(byte)의 기준은 사용하는 데이터베이스의 환경변수 설정에 따라 달라진다. 예를 들어 UTF-8 환경에서의 알파벳 'a'는 1byte 한글 '가'는 3byte의 저장소가 필요하므로 LENGTH 함수는 3으로 보여진다. Byte가 아닌 순수한 문자의 수는 CHAR_LENGTH 함수를 사용하여 확인할 수 있다.

#### REPLACE함수 
REPLACE()는 문자열을 치환하는 함수이다. 
- 도서제목에 야규가 포함된 도서를 농구로 변경한 후 도서 목록을 보이시오.
```
SELECT bookid, REPLACE(bookname, '야구','농구') bookname, publisher, price 
FROM Book;
```

#### LENGTH, CHAR_LENGTH 함수
- LENGTH()는 바이트(byte) 수를 가져오는 함수이다. 일반적으로 알파벳 'A'는 1바이트, 한글 '가'는 3바이트(UTF-8의 경우)이다.
- CHAR_LENGTH() 함수는 문자의 수를 가져오는 함수로 알파벳과 한글 모두 결과를 1로 반환한다. 즉, 세는 단위가 바이트가 아니라 문자다. 이 때 공백 역시 하나의 문자로 간주한다.

- 굿스포츠에서 출판한 도서의 제목과 제목의 문자 수, 바이트 수를 보이시오.
```
SELECT bookname '제목', CHAR_LENGTH(bookname) '문자수', 
	LENGTH(bookname) '바이트수'
FROM Book
WHERE publisher='굿스포츠';
```

#### SUBSTR 함수
SUBSTR()은 문자열 중 특정 위치에서 시작하여 지정한 길이만큼의 문자열을 반환하는 함수이다.

- 마당서점 고객 중에서 같은 성씨를 가진 사람이 몇 명이나 되는지 성별 인원수를 구하시오.

```
SELECT SUBSTR(name, 1, 1) '성', COUNT(*) '원'
FROM Customer
GROUP BY SUBSTR(name, 1, 1);
```

> GROUP BY 절에는 열 이름 뿐만 아니라 함수를 포함할 수도 있다.

### 날짜,시간 함수

#### 날짜, 시간 함수의 종류

|함수|반환형|설명|
|-----|----|------|
|STR_TO_DATE(string, format)|DATE|문자열(STRING) 데이터를 날짜형(DATE)으로 반환<br>STR_TO_DATE('2019-02-14', '%Y-%n-%d') -> 2019-02-14|
|DATE_FORMAT(date, format)|STRING|날짜형(DATE) 데이터를 문자열(VARCHAR)로 반환<br>DATE_FORMAT('2019-02-14', '%Y-%m-%d') -> 2019-02-14|
|ADDDATE(date, interval)|DATE|DATE형의 날짜에서 INTERVAL 지정한 시간만큼 더함<br>ADDDATE('2019-02-14', INTERVAL 10 DAY) -> 2019-02-24|
|DATE(date)|DATE|DATE형의 날짜 부분을 반환<br>예) DATE('2003-12-31 01:02:03') -> 2003-12-31|
|DATEDIFF(date1, date2)|INTEGER|DATE형의 date1 - date2의 차이를 반환<br>DATEDIFF('2019-02-14', '2019-02-04') -> 10|
|SYSDATE|DATE|DMBS 시스템상의 오늘 날짜를 반환하는 함수<br>SYSDATE() -> 2019-06-30 21:47:01|

#### format의 주요 지정자

|인자|설명|
|----|------|
|%w|요일 순서(0~6, Sunday=0)|
|%W|요일(Sunday ~ Saturday)|
|%a|요일의 약자(Sun~Sat)|
|%d|1달 중 날짜(00~31)|
|%i|1년 중 날짜(001~366)|
|%h|12시간(01~12)|
|%H|24시간(00~23)|
|%i|분(0~59)|
|%m|월 순서(01~12,  January=01)|
|%b|월 이름 약어(Jan~Dec)|
|%M|월 이름(January~December)|
|%s|초(0~59)|
|%Y|4자리 연도|
|%y|4자리 연도의 마지막 2 자리|


- 마당서점은 주문일로부터 10일 후 매출을 확정한다. 각 주문의 확정일자를 구하시오
```
SELECT orderid '주문번호', orderdate '주문일', ADDDATE(orderdate, INTERVAL 10 DAY) '확정'
FROM Orders;
```

#### STR_TO_DATE 함수, DATE_FORMAT 함수
- STR_TO_DATE 함수는 CHAR 형(문자열)으로 저장된 날짜를 DATE형식으로 변환하는 함수이다.
- DATE_FORMAT 함수는 STR_TO_DATE 함수와 반대로 날짜형으로 문자형으로 변환한다.

- 마당서점이 2014년 7월 7일에 주문받은 도서의 주문번호, 주문일, 고객번호, 도서번호를 모두 보이시오. 단, 주문일은 %Y-%m-%d 형태로 표시한다.

```
SELECT orderid '주문번호', STR_TO_DATE(orderdate, '%Y-%m-%d') '주문일', 
	custid '고객번호', bookid '도서번호'
FROM Orders
WHERE orderdate=DATE_FORMAT('20140707', '%Y%m%d');
```

#### SYSDATE 함수
SYSDATE함수는 MySQL 데이터베이스에 설정된 현재 날짜와 시간을 반환하는 함수이다.
- DBMS 서버에 설정된 현재 날짜와 시간 요일을 확인하시오.
```
SELECT SYSDATE(), 
	DATE_FORMAT(SYSDATE(), '%Y/%m/%d %M %h:%i) 'SYSDATE_1';
```

### NULL 값 처리
- NULL 값이란 아직 지정되지 않은 값을 말한다.
- 지정되지 않았다는 것은 값을 알 수도 없고 적용할 수도 없다는 뜻이다.
- NULL값은 '0', ''(빈문자), ' '(공백) 등과 다른 특별한 값임을 유의해야 한다.
- NULL값은 비교 연산자로 비교가 불가능하다. NULL값은 아직 지정되지 않은 값이므로 =, \<, \> 등과 같은 연산자로 비교하지 못한다. 
- 또한 NULL값의 연산을 수행하면 결과 역시 NULL 값으로 반환된다.

#### NULL 값에 대한 연산과 집계 함수
집계 함수를 사용할 때 NULL 값이 포함된 행에 대하여 다음과 같은 주의가 필요하다.
- 'NULL + 숫자' 연산의 결과는 NULL이다.
- 집계 함수를 계산할 때 NULL이 포함된 행은 집계에서 빠진다.
- 해당되는 행이 하나도 없을 경우 SUM, AVG 함수의 결과는 NULL이 되고, COUNT 함수의 결과는 0이다.

```
SELECT price+100
FROM Mybook 
WHERE bookid=3
```

```
SELECT SUM(price), AVG(price), COUNT(*), COUNT(price)
FROM MyBook
```

```
SELECT SUM(price), AVG(price), COUNT(*)
FROM Mybook
WHERE bookid >= 4;
```

#### NULL 값을 확인하는 방법 - IS NULL, IS NOT NULL
NULL 값을 찾을 때는 '=' 연산자가 아닌 'IS NULL'을 사용하고 NULL이 아닌 값을 찾을 때는 '\<\>'연산자가 아닌 'IS NOT NULL'을 사용해야 한다.

```
SELECT * 
FROM Mybook 
WHERE price IS NULL;
```
위의 SQL 문을 다음과 같이 작성하면 결과는 틀리다.
```
SELECT * 
FROM Mybook 
WHERE price='';
```

#### IFNULL 함수
- IFNULL 함수는 NULL 값을 다른 값으로 대치하여 연산하거나 다른 값으로 출력하는 함수이다. 
- IFNULL 함수를 사용하면 NULL 값을 임의의 다른 값으로 변경할 수 있다.

```
IFNULL(속성, 값) /* 속성 값이 NULL이면, '값'으로 대치한다. */
```

- 이름, 전화번호가 포함된 고객목록을 보이시오. 단, 전화번호가 없는 고객인 '연락처없음'으로 표시하시오.
```
SELECT name '이름', IFNULL(phone, '연락처없음') '전화번호' 
FROM Customer;
```

### 행번호 출력
MySQL에서는 변수를 사용하여 처리하는 방법이 있다. MySQL에서 변수는 이름 앞에 @기호를 붙이며 치환문에는 SET := 기호를 사용한다.
- 고객 목록에서 고객번호, 이름, 전화번호를 앞의 두 명만 보이시오.

```
SET @seq:=0;
SELECT (@seq:=@seq+1) ' 순번', custid, name, phone
FROM Customer
WHERE @seq < 2;
```

* * * 
## 부속질의

### 스칼라 부속질의 - SELECT 부속질의
- 스칼라 부속질의(scalar subquery)는 SELECT 절에서 사용되는 부속질의로 부속질의의 결과 값을 단일 행, 단일 열의 스칼라 값으로 반환한다.
- 만약 결과 값이 다중 행이거나 다중 열이라면 DBMS는 그 중 어떠한 행, 어떠한 열을 출력해야 하는지 알 수 없어 에러를 출력한다.
- 결과가 없는 경우에는 NULL 값을 출력한다.
- 스칼라 부속질의는 원칙적으로 스칼라 값이 들어갈 수 있는 모든 곳에 사용가능하며, 일반적으로 SELECT문과 UPDATE SET 절에 사용된다.
- 주질의와 부속질의와의 관계는 상관/비상관 모두 가능하다.

- 마당 서점의 고객별 판매액을 보이시오(고객이름과 고객별 판매액 출력)
```
SELECT (SELECT name
	FROM Customer cs
	WHERE cs.custid=od.custid) 'name', SUM(saleprice) 'total'
FROM Orders od
GROUP BY od.custid;
```

- 다음의 실습을 위해 새로운 속성인 도서이름(bname)을 추가하시오
```
ALTER TABLE Orders ADD bname VARCHAR(40);
```

- Orders 테이블에 각 주문에 맞는 도서이름을 입력하시오.
```
UPDATE Orders 
SET bname=(SELECT bookname
	FROM Book
	WHERE Book.bookid=Orders.bookid);
```

### 인라인 뷰 - FROM 부속질의
- 인라인 뷰(inline view)는 FROM 절에서 사용되는 부속질의를 말한다.
- 뷰(view)는 기존 테이블로부터 일시적으로 만들어진 가상의 테이블을 말한다.
- SQL의 FROM 절에는 테이블 이름이 위치하는데, 여기에 테이블 이름 대신 인라인 뷰 부속질의를 사용하면 보통의 테이블과 같은 형태로 사용할 수 있다.
- 부속질의 결과 반환되는 데이터는 바중 행, 다중 열이어도 상관없다. 
- 다만 가상의 테이블인 뷰 형태로 제공되기 때문에 상관 부속질의로 사용될 수는 없다.

- 고객번호가 2 이하인 고객의 판매액을 보이시오(고객이름과 고객별 판매액 출력)

```
SELECT cs.name, SUM(od.saleprice) 'total' 
FROM (SELECT custid, name
	FROM Customer
	WHERE custid <= 2) cs,
	Orders od 
WHERE cs.custid=od.custid
GROUP BY cs.name;
```

### 중첩질의 - WHERE 부속질의
- 중첩질의(nested query)는 WHERE 절에서 사용되는 부속질의를 말한다.
- WHERE  절은 보통 테이터를 선택하는 조건 혹은 술어(predicate)와 같이 사용된다. 그래서 중첩질의를 술어 부속질의(predicate subquery)라고도 부른다.

#### 중첩질의 연산자의 종류

|술어|연산자|반환 행|반환 열|상관|
|----|-----|-----|-----|----|
|비교|=, \>, \<, \>=,\<=,\<\>|단일|단일|가능|
|집합|IN, NOT IN|다중|다중|가능|
|한정|ALL, SOME(ANY)|다중|단일|가능|
|존재|EXISTS, NOT EXISTS|다중|다중|필수|

#### 비교 연산자
- 비교 연산자는 부속질의가 반드시 단일 행, 단일 열을 반환해야 하며, 아닐 경우 질의를 처리할 수 없다.
- 처리과정은 주질의의 대상 열 값과 부속질의의 결과 값을 비교 연산자에 적용하여 참이면 주질의의 해당 열을 출력한다.

- 평균 주문금액 이하의 주문에 대해서 주문번호와 금액을 보이시오.
```
SELECT orderid, saleprice
FROM Orders
WHERE saleprice <= (SELECT AVG(saleprice)
		FROM Orders);
```

- 각 고객의 평균 주문금액보다 큰 금액의 주문 내역에 대해서 주문번호, 고객번호, 금액을 보이시오.

```
SELECT orderid, custid, saleprice
FROM Orders md
WHERE saleprice > (SELECT AVG(saleprice)
		FROM Orders so
		WHERE md.custid = so.custid);
```
- 상관 부속질의 형태로, 주질의는 각 행별로 고객번호를 부속질의에 공급한다. 부속질의는 그 값을 가지고 고객별 평균을 구하여 주질의에 해당 행과 비교연산을 수행한다.

#### IN, NOT IN
- IN 연산자는 주질의의 속성 값이 부속질의에서 제공한 결과 집합에 있는지 확인하는 역할을 한다. 
- IN 연산자에서 사용 가능한 부속질의는 결과로 다중 행, 다중 열을 반환할 수 있다.
- 주질의는 WHERE 절에 사용되는 속성 값을 부속질의의 결과 집합과 비교해 하나라도 있으면 참이된다. NOT IN은 이와 반대로 값이 존재하지 않으면 참이 된다.

- 대한민국에 거주하는 고객에게 판매한 도서의 총 판매액을 구하시오
```
SELECT SUM(saleprice) 'total'
FROM Orders
WHERE custid IN (SELECT custid
	FROM Customer
	WHERE address LIKE '%대한민국%');
```
- IN 연산자는 다중 행을 반환할 수 있으므로 주질의의 custid를 부속질의의 결과 집합과 비교할 수 있다.
- 만약 구하려는 데이터가 '대한민국에 거주하지 않는 고객'이었을 경우는 NOT IN 연산자를 사용하면 된다.

#### EXISTS, NOT EXISTS
EXISTS와 NOT EXISTS는 데이터의 존재 유무를 확인하는 연산자이다.

```
WHERE [NOT] EXISTS (부속질의)
```

- EXISTS 연산자를 사용하여 대한민국에 거주하는 고객에게 판매한 도서의 총 판매액을 구하시오.

```
SELECT SUM(saleprice) 'total'
FROM Orders od
WHERE EXISTS (SELECT * 
	FROM Customer cs
	WHERE address LIKE '%대한민국%' AND cs.custid=od.custid);
```

* * *
## 뷰 
- 뷰(view)는 하나 이상의 테이블을 합하여 만든 가상의 테이블이다. 합한다는 말은 앞서 배운 SELECT 문을 통해 얻은 최종 결과를 뜻하며
- 뷰는 이러한 결과를 가상의 테이블로 정의하여 실제 테이블처럼 사용할 수 있도록 만든 데이터베이스 개체이다.
- 뷰는 테이블 처럼 사용할 수 있지만 SELECT 문을 제외한 일부 물리적인 테이블의 갱신작업을 수행하는 데 제약이 있다. INSERT, UPDATE, DELETE 등의 DML 작업은 경우에 따라 수행되지 않는다.

### 뷰의 생성
- 뷰는 실제 SQL 문에서 테이블과 동일하게 사용할 수 있는 데이터베이스 개체이다. 
- 뷰는 사용자가 직접 정의하는 과정을 거치는데, 뷰의 정의를 뷰의 생성이라고도 한다.

```
CREATE VIEW 뷰이름 [(열이름 [,...n])]
AS SELECT 문 
```
- '뷰이름'은 생성할 뷰의 이름을 말하며, '열이름'은 뷰에서 사용할 열의 이름을 말한다. 
- 열 이름과 SELECT 문에서 추출하는 속성은 일대일로 대응된다.
- SELECT 문은 뷰를 정의하는 역할을 한다.

- 주소에 '대한민국'을 포함하는 고객들로 구성된 뷰를 만들고 조회하시오. 뷰의 이름은 vw_Customer로 설정하시오.
```
CREATE VUEW vw_Customer
AS SELECT * 
	FROM Customer
	WHERE address LIKE '%대한민국%';
```

- 결과확인 
```
SELECT * 
FROM vw_Customer;
```

- Orders 테이블에서 고객이름과 도서이름을 바로 확인할 수 있는 뷰를 생성한 후 '김연아' 고객이 구입한 도서의 주문번호, 도서이름, 주문액을 보이시오.

```
CREATE VIEW vw_Orders (orderid, custid, name, bookid, bookname, saleprice, orderdate)
AS SELECT od.orderid, od.custid, cs.name,
	od.bookid, bk.bookname, od.saleprice, od.orderdate
FROM Orders od, Customer cs, Book bk 
WHERE od.custid = cs.custid AND od.bookid=bkbookid;
```

- 결과확인
```
SELECT orderid, bookname, saleprice
FROM vw_Orders
WHERE name='김연아';
```

### 뷰의 수정
뷰의 수정은 CREATE VIEW 문에 OR REPLACE 명령을 더하여 작성한다.

```
CREATE OR REPLACE VIEW 뷰이름 [(열이름 [..., n])]
AS SELECT 문 
```

- 이미 생성한 뷰 vw_Customer는 주소가 대한민국인 고객을 보여준다. 이 뷰를 영국을 주소로 가진 고객으로 변경하시오. phone 속성은 필요 없으므로 포함시키지 마시오.

```
CREATE OR REPLACE VIEW vw_Customer (custid, name, address)
AS SELECT custid, name, address
	FROM Customer
	WHERE address LIKE '%영국%';
```

- 결과확인
```
SELECT *
FROM vw_Customer;
```
### 뷰의 삭제
뷰가 필요 없게 된 경우에는 DROP  문을 사용하여 뷰를 삭제한다.
```
DROP VIEW 뷰이름 [,... n];p
```

- 앞서 생성한 뷰 vw_Customer를 삭제하시오.
```
DROP VIEW vw_Customer;
```
