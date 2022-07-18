# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1tVqDYixHNRwQiN6S_QZPRQl9m8HUqNSx?usp=sharing)

## 데이터 조작어(DML) - 검색

### WHERE 조건
- WHERE절은 조건에 맞는 검색을 할 때 사용한다. 
- 조건으로 사용할 수 있는 술어(predicate)는 비교, 범위, 집합, 패턴, NULL로 구분할 수 있다.

#### WHERE 절에 조건으로 사용할 수 있는 술어

|술어|연산자|사용 예|
|----|-----|-------|
|비교|=,\<\>, \<=, \>, \>=|price \< 20000|
|범위|BETWEEN|price BETWEEN 10000 AND 20000|
|집합|IN, NOT IN|price IN (10000, 20000, 30000)|
|패턴|LIKE|bookname LIKE '축구의 역사'|
|NULL|IS NULL, IS NOT NULL|price IS NULL|
|복합조건|AND, OR, NOT|price < 20000 AND bookname LIKE '축구의 역사'|

#### 비교
- 단순비교는 =,\<\>, \<=, \>, \>= 등을 사용한다. 
- 가격이 20,000원 미만인 도서를 검색하시오.
```
SELECT * 
FROM Book 
WHERE price < 20000;
```

#### 범위
- WHERE 절에서 BETWEEN 연산자를 사용하면 값의 범위를 지정할 수 있다.
- 가격이 10,000원 이상 20,000원 이하인 도서를 검색하시오.
```
SELECT * 
FROM Book
WHERE price BETWEEN 10000 AND 20000;
```

- BETWEEN은 논리 연산자인 AND를 사용하여 다음과 같이 사용할 수 있다.
```
SELECT * 
FROM Book
WHERE price >= 10000 AND price <= 20000;
```

#### 집합 
- WHERE 절에 두 개 이상의 값을 비교하려면 IN 연산자와 NOT IN 연산자를 사용하면 편리하다.
- IN 연산자는 집합의 원소인지 판단하는 연산자다.
- 출판사가 '굿스포츠' 혹은 '대한미디어'인 도서를 검색하시오.

```
SELECT * 
FROM Book 
WHERE publisher IN ('굿스포츠', '대한미디어');
```

- 출판사가 '굿스포츠' 혹은 '대한미디어'가 아닌 출판사를 검색하는 SQL문은 다음과 같다.
```
SELECT * 
FROM Book 
WHERE publisher NOT IN ('굿스포츠', '대한미디어');
```

#### 패턴
- 문자열의 패턴을 비교할 때는 LIKE 연산자를 사용한다.
- 축구의 역사를 출간한 출판사를 검색하시오.
```
SELECT bookname, publisher 
FROM Book
 WHERE bookname LIKE '축구의 역사';
```

- 도서이름에 '축구'가 포함된 도서를 찾고 싶다면 와일드 문자 '%'를 사용한다. '%'는 임의의 문자열을 대신하는 기호이다.
```
SELECT bookname, publisher
FROM Book
WHERE bookname LIKE '%축구%';
```

- 와일드 문자 \_(밑줄기호)는 측정 위치에 한 문자만 대신할 때 사용한다.
- 도서이름의 왼쪽 두 번째 위치에 '구'라는 문자열을 갖는 도서를 검색하시오.
```
SELECT * 
FROM Book
WHERE bookname LIKE '_구%';
```

#### 문자열 검색 시 LIKE와 같이 사용하는 와일드 문자

|와일드 문자|의미|사용 예|
|----|-----|-------|
|\+|문자열을 연결|'골프'+'바이블' : '골프 바이블'
|%|0개 이상의 문자열과 일치|'%축구%' : 축구를 포함하는 문자열|
|\[\]|1개의 문자와 일치|'\[0-5\]' : 0-5 사이 숫자로 시작하는 문자열|
|\[^\]|1개의 문자와 불일치|'\[^0-5\]%' : 0-5 사이 숫자로 시작하지 않는 문자열|
|_|특정 위치의 1개의 문자와 일치|'_구%' : 두 번째 위치에 '구'가 들어가는 문자열|

#### 복합조건

- WHERE 절에 논리 연산자 AND, OR, NOT을 사용하면 복합조건을 명시할 수 있다.
- 축구에 관한 도서 중 가격이 20,000원 이상인 도서를 검색하시오.
```
SELECT * 
FROM Book 
WHERE bookname LIKE '%축구%' AND price >= 20000;
```

- 출판사가 '굿스포츠' 혹은 '대한미디어'인 도서를 검색하시오.
```
SELECT * 
FROM Book
WHERE publisher='굿스포츠' OR publisher='대한미디어';
```
>같은 속성에서 여러 개 값을 비교하여 찾을 때는 앞에서 배운 OR 연산자보다는 **IN 연산자**를 사용하는 것이 바람직하다.

### ORDER BY
- SQL 문의 실행 결과 행의 순서는 각 DBMS에 저장된 위치에 따라 결정된다.
- SQL 문의 실행 결과를 특정 순서대로 출력하고 싶으면 ORDER BY 절을 사용한다.

- 도서를 이름순으로 검색하시오.
```
SELECT * 
FROM Book
ORDER BY bookname;
```

- 도서를 가격순으로 먼저 정렬한 후 가격이 도서에 대하여 이름순으로 다시 정렬하고 싶으면 ORDER BY 절에서 정렬을 원하는 열 이름을 순서대로 지정한다.
- 도서를 가격순으로 검색하고, 가격이 같으면 이름순으로 검색하시오.
```
SELECT * 
FROM Book
ORDER BY price, bookname;
```

- 정렬의 기본은 오름차순이다.
- 만약 내림차순으로 정렬하려면 열 이름 다음에 DESC 키워드를 사용하면 된다.

- 도서를 가격의 내림차순으로 검색하시오. 만약 가격이 같다면 출판사의 오름차순으로 출력하시오.
```
SELECT * 
FROM Book 
ORDER BY price DESC, publish ASC;
```

### 집계함수와 GROUP BY
- 집계를 하기 위해서는 GROUP BY 문을 사용하고 구체적인 집계 내용은 집계함수를 사용한다.
> 집계(aggregate)는 통계(statistics)와 비슷한 의미로, 데이터베이스에서는 통계보다는 집계라는 용어를 사용한다.

#### 집계함수
- 고객이 주문한 도서의 총 판매액을 구하시오.
```
SELECT SUM(saleprice)
FROM Orders;
```
- SQL 문에서 SUM(saleprice)는 저장된 데이터를 가공하여 얻은 새로운 결과 열이기 때문에 결과 테이블에 별도의 이름 없이 'SUM(saleprice)'라고 출력된다.
- 이는 보기에 좋지 않다. 의미 있는 열 이름을 출력하고 싶으면 속성이름의 별칭을 지칭하는 **AS 키워드**를 사용하여 열 이름을 부여한다. 
```
SELECT SUM(saleprice) AS 총매출
FROM Orders;
```
> 최근 버전의 DBMS에서는 다음과 같이 AS를 생략할 수 있다. <br>SELECT SUM(saleprce) 총매출 FROM Orders;
> 또 속성이름의 별칭 중간에 공백을 넣어야 할 경우 다음과 같이 큰 따옴표를 사용한다.<br>SELECT SUM(saleprice) "전체 매출" FROM Orders;

- 2번 김연아 고객이 주문한 도서의 총 판매액을 구하시오.
```
SELECT SUM(saleprice) AS 총매출
FROM Orders 
WHERE custid=2;
```

- 집계함수는 여러 개를 혼합하여 쓸 수 있다.
```
SELECT SUM(saleprice) AS Total,
		  AVG(saleprice) AS Average,
		  MIN(saleprice) AS Minimum,
		  MAX(saleprice) AS Maximum
FROM Orders;
```
- 집계함수 COUNT는 행의 개수를 센다. 
- COUNT()의 괄호 안에는 \*혹은 특정 속성의 이름이 사용되며, 해당 속성의 투플의 개수를 세어준다(NULL 값은 제외).
- 예를 들어 COUNT(\*)는 전체 투플의 수를 세어주며, COUNT(publisher)는 출판사의 수를, COUNT(DISTINCT publisher)는 중복을 제거한 출판사의 수를 세어준다(NULL 값은 제외).
- 마당서점의 도서 판매 건수를 구하시오.

```
SELECT COUNT(*) 
FROM Orders;
```

#### 집계함수의 종류
문법에서 '\[\]' 기호는 선택을 나타내며, '|' 기호는 둘 중 하나를 선택하라는 의미이다.

|집계 함수|문법|사용 예|
|----|--------|----|
|SUM|SUM(\[ALL \| DISTINCT\] 속성이름)|SUM(price)|
|AVG|AVG(\[ALL \| DISTINCT\] 속성이름)|AVG(price)|
|COUNT|SUM(\[ALL \| DISTINCT \| \*\] 속성이름)|COUNT(\*)|
|MAX|MAX(\[ALL \| DISTINCT\] 속성이름)|MAX(price)|
|MIN|MIN(\[ALL \| DISTINCT\] 속성이름)|MIN(price)|


### GROUP BY
SQL문에서 GROUP BY절을 사용하면 속성 값이 같은 값끼리 그룹을 만들 수 있다.

- 고객별로 주문한 도서의 총 수량과 총 판매액을 구하시오.
```
SELECT custid, COUNT(*) AS 도서수량, SUM(saleprice) AS 총액
FROM Orders
GROUP BY custid; 
```

- **HAVING 절**은 **GROUP BY 절의 결과 나타나는 그룹을 제한**하는 역할을 한다.
- 가격이 8,000원 이상인 도서를 구매한 고객에 대해서 고객별 주문 도서의 총 수량을 구하시오. 단 두권 이상 구매한 고객만 구하시오.
```
SELECT custid, COUNT(*) AS 도서수량
FROM Orders
WHERE saleprice >= 8000
GROUP BY custid 
HAVING COUNT(*) >= 2;
```

#### GROUP BY와 HAVING 절의 문법과 주의사항
- GROUP BY \<속성\><br>
GROUP BY로 투플을 그룹으로 묶은 후 SELECT 절에는 GROUP BY에서 사용한 \<속성\>과 집계 함수만 나올 수 있다.<br>
	- 맞는 예
	```
	 SELECT custid, SUM(saleprice)
	 FROM Orders
	 GROUP BY custid
	```
	- 틀린 예
	```
	SELECT bookid, SUM(saleprice) /* SELECT 절에 bookid 속성이 올수 없다.
	FROM Orders 
	GROUP BY custid;
	```
	
- HAVING \<검색조건\><br>
WHERE 절과 HAVING 절이 같이 포함된 SQL문은 검색조건이 모호해질 수 있다. 
- HAVING절은 반드시 GROUP BY절과 같이 작성해야 하고
- WHERE 절보다 뒤에 나와야 한다. 
- 그리고 \<검색조건\>에는 SUM, AVG, MAX, MIN, COUNT와 같은 집계함수가 와야 한다.
	- 맞는 예 
	```
	SELECT custid, COUNT(*) AS 도서수량
	FROM Orders 
	WHERE saleprice >= 8000
	GROUP BY custid
	HAVING count(*) >= 2;
	```
	- 틀린 예 
	```
	SELECT custid, COUNT(*) AS 도서수량
	FROM Orders 
	HAVING COUNT(*) > 2 /* 순서가 틀렸다 */
	WHERE saleprice >= 8000
	GROUP BY custid;
	```
	
### 두 개 이상의 테이블에서 SQL 질의
SQL은 여러 개의 테이블을 질의하는 두 가지 방법을 제공한다. <b>조인(join)과 부속질의(subquery)</b> 두 가지 모두 여러 개의 테이블을 동시에 다루지만 방법은 약간 다르다.

### 조인
조인(join)은 한 테이블의 행을 다른 테이블의 행에 연결하여 두 개 이상의 테이블을 결합하는 연산이다.

- 두 테이블을 아무런 조건을 주지 않고 SELECT 시키면 관계대수의 카티전 프로덕트 연산(**단순 순서쌍**)이 된다.
```
SELECT * 
FROM Customer, Orders;
```
- 그러나 결과는 논리에 맞지 않다.  이것은 SQL문의 WHERE 절에 두 테이블의 연결 조건을 추가함으로써 쉽게 해결할 수 있다.
- 고객과 고객의 주문에 관한 데이터를 모두 보이시오.
```
SELECT * 
FROM Customer, Orders
WHERE Customer.custid = Orders.custid;
```

- 고객과 고객의 주문에 관한 데이터를 고객별로 정렬하여 보이시오.
```
SELECT * 
FROM Customer, Orders 
WHERE Customer.custid = Orders.custid
ORDER BY Customer.custid;
```

> 열 이름을 표기하는 방법<br>앞의 두 SQL 문에서 Customer.custid, Orders.custid와 같이 표현된 것을 볼 수 있다. 이는 '테이블 이름.열 이름' 형식의 표현으로 열 이름이 어느 테이블과 연관되는지 정확히 명시한다.

- 여러 개의 테이블을 연결하여 하나의 테이블을 만드는 과정을 테이블 조인이라고 한다. 
- 특히 앞의 SQL 문처럼 동등조건에 의하여 테이블을 조인하는 것을 <b>동등조인(equal join)</b>이라고 한다.

- 고객의 이름과 고객이 주문한 도서의 판매가격을 검색하시오.
```
SELECT name, saleprice 
FROM Customer, Orders
WHERE Customer.custid = Orders.custid;
```

- 모든 SQL 질의의 결과는 단일 테이블이다. 따라서 위 결과 테이블에 SQL 문법을 적용할 수 있다. 예를 들면 GROUP BY 절과 ORDER BY 절을 추가하면 다음과 같다.

- 고객별로 주문한 모든 도서의 총 판매액을 구하고, 고객별로 정렬하시오.
```
SELECT name, SUM(saleprice) 
FROM Customer, Orders 
WHERE Customer.custid = Orders.custid 
GROUP BY Customer.name
ORDER BY Customer.name;
```

- 세 개 이상의 테이블을 조인할 수도 있다.
- 고객의 이름과 고객이 주문한 도서의 이름을 구하시오
```
SELECT Customer.name, Book.bookname
FROM Customer, Orders, Book
WHERE Customer.custid = Orders.custid AND Orders.bookid = Book.bookid;
```

- 가격이 20,000원인 도서를 주문한 고객의 이름과 도서의 이름을 구하시오.
```
SELECT Customer.name, Book.bookname
FROM Customer, Orders, Book
WHERE Customer.custid=Orders.custid AND Orders.bookid = Book.bookid AND Book.price = 20000;
```

- 조인 연산의 특별한 경우로 <b>외부 조인(outer join)</b>이 있다. 고객의 이름과 고객이 주문한 도서의 판매가격을 구하는 동등조인의 예에서 도서를 주문하지 않은 고객 '박세리'는 결과에 포함되지 않는다. 만약 **도서를 구매하지 않은 고객 '박세리'까지 포함하여** 고객의 이름과 고객이 주문한 도서의 가격을 구하려면 어떻게 해야 할까? 방법은 **외부조인**을 사용하면 된다.

- 도서를 구매하지 않은 고객을 포함하여 고객의 이름과 고객이 주문한 도서의 판매가격을 구하시오.
```
SELECT Customer.name, saleprice 
FROM Customer LEFT OUTER JOIN Orders 
		ON Customer.custid = Orders.custid;
```
- 결과를 보면 고객 박세리의 saleprice 값이 NULL로 표시되어 있다. 왼쪽 외부조인인 LEFT OUTER JOIN .. ON 문법으로 질의한다. 
- 오른쪽에 있는 테이블에 대하여 같은 방법으로 질의하려면 RIGHT OUTER JOIN ... ON을 사용하고, 
- 왼쪽과 오른쪽 테이블 모두에 대하여 질의하려면 FULL OUTER JOIN ... ON을 사용하면 된다.

#### 조인 문법

<table>
<thead>
	<tr>
		<th>명령</th>
		<th>문법</th>
		<th>설명</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td rowspan='2'>일반적인 조인</td>
		<td>SELECT <속성들><br>FROM 테이블 1, 테이블 2<br>WHERE <조인조건> AND <검색조건></td>
		<td rowspan='2'>SQL 문에서는 주로 동등조인을 사용한다. 두 가지 문법 중 하나를 사용할 수 있다.</td>
	</tr>
	<tr>
		<td>SELECT <속성들><br>FROM 테이블 1 INNER JOIN 테이블 2 ON <조인조건><br>WHERE <검색조건></td>
	</tr>
	<tr>
		<td>외부조인</td>
		<td>SELECT <속성들><br>FROM 테이블 1 {LEFT | RIGHT | FULL [OUTER]} JOIN 테이블 2 ON <조인조건><br>WHERE <검색조건></td>
		<td>외부조인은 FROM 절에 조인 종류를 적고 ON을 이용하여 조인조건을 명시한다.</td>
	</tr>
</tbody>
</table>

### 부속질의
- 가격이 가장 비싼 도서의 이름은 얼마인가? 라는 질문에 대한 답을 구한다고 생각해 보자, 가장 비싼 도서의 가격은 다음과 같이 구할 수 있으며 답은 35,000원 이다. 
```
SELECT MAX(price)
FROM Book;
```

- 만약 가장 비싼 도서의 가격을 알고 있다면 다음과 같이 가격이 35,000원인 도서의 이름을 바로 검색하면 된다.
```
SELECT bookname 
FROM Book
WHERE price=35000;
```

- 이 두 질의를 하나의 질의로 작성할 수 있을까? 가능하다. 두 번째 질의르 35,000 값의 위치에 첫 번째 질의를 대치하면 된다.
- 가장 비싼 도서의 이름을 보이시오.
```
SELECT bookname
FROM Book 
WHERE price = (SELECT MAX(price) FROM Book);
```
- SELECT 문의 WHERE 절에 또 다른 테이블 결과를 이용하기 위해 다시 SELECT문을 괄호로 묶는 것을 <b>부속질의(subquery)</b>라고 한다.
- 부속질의는 질의가 중첩되어 있다는 의미에서 <b>중첩질의(nested query)</b>라고도 한다.

- 도서를 구매한 적이 있는 고객의 이름을 검색하시오.
```
SELECT name 
FROM Customer
WHERE custid IN (SELECT custid FROM Orders);
```

- 세 개 이상 중첩된 부속질의도 가능하다.
- 대한미디어에서 출판단 도서를 구매한 고객의 이름을 보이시오.
```
SELECT name 
FROM Customer
WHERE custid IN (SELECT custid 
		FROM Orders
		WHERE bookid IN (SELECT bookid 
				FROM Book 
				WHERE publisher='대한미디어'));
```

- 부속질의 간에는 상하 관계가 있으며, 실행 순서는 **하위 부속질의를 먼저 실행하고 그 결과를 이용하여 상위 부속질의를 실행**한다. 
- 반면 <b>상관 부속질의(correlated subquery)</b>는 상위 부속질의의 투플을 이용하여 하위 부속질의를 계산한다. 즉, **상위 부속질의와 하위 부속질의가 독립적이지 않고 서로 관련을 맺고 있다.**

- 출판사별로 출판사의 평균 도서 가격보다 비싼 도서를 구하시오.
```
SELECT b1.bookname 
FROM Book b1
WHERE b1.price > (SELECT avg(b2.price)
				FROM Book b2
				WHERE b2.publisher = b1.publisher);
```
> 투플 변수<br>테이블 이름이 길거나 한 개의 테이블이 SQL 문에 두 번 사용될 때 혼란을 피하기 위해 테이블의 별칭을 붙여 사용하는데, 이를 <b>투플 변수(tuple variable)</b>라 한다. 투플 변수는 FROM 절의 테이블 이름 뒤에 표기한다.

- 부속질의와 조인의 차이점
	- 부속질의는 SELECT 문에 나오는 결과 속성을 FROM 절의 테이블에서만 얻을 수 있고 
	- 조인은 조인한 모든 테이블에서 결과 속성을 얻을 수 있다.
	- 조인은 부속질의가 할 수 있는 모든 것을 할 수 있다.
	- 그러나 한 개의 테이블에서만 결과를 얻는 여러러 테이블 질의는 조인보다 부속질의로 작성하는 것이 훨씬 편하다.
	
	
### 집합 연산
- SQL문의 결과는 테이블로 나타난다. 
- 테이블은 투플의 집합이므로 테이블 간의 집합 연산을 이용하여 합집합, 차집합, 교집합을 구할 수 있다. SQL에서 집합 연산 중 합집합을 UNION으로 나타낸다.(MySQL은 다른 DBMS와 달리 MINUS, INTERSECT 집합 연산이 없다. MINUS 연산은 NOT IN, INTERSECT 연산은 IN 연산자를 이용하여 구한다.)

- 대한민국에서 거주하는 고객의 이름과 도서를 주문한 고객의 이름을 보이시오.
```
SELECT name 
FROM Customer
WHERE address LIKE '대한민국%'
UNION 
SELECT name 
FROM Customer
WHERE custid IN (SELECT custid FROM Orders);
```

- UNION 연산자 외에 UNION ALL 연산자는 중복을 포함하여 모든 결과를 구한다.
```
SELECT name 
FROM Customer
WHERE address LIKE '대한민국%'
UNION ALL
SELECT name 
FROM Customer
WHERE custid IN (SELECT custid FROM Orders);
```

### EXISTS 
- EXISTS는 상관 부속질의문 형식이다.
- EXISTS는 원래 단어에서 의미하는 것과 같이 조건에 맞는 투플이 존재하면 결과에 포함시킨다. 즉, 부속질의문의 어떤 행이 조건에 만족하면 참이다.
- 반면 NOT EXISTS는 부속질의문이 모든 행이 조건에 만족하지 않을 때만 참이다.
- EXISTS와 NOT EXISTS는 상관 부속질의문의 다른 형태이다.

- 주문이 있는 고객의 이름과 주소를 보이시오.
```
SELECT name, address
FROM Customer cs
WHERE EXISTS (SELECT * 
		FROM Orders od 
		WHERE cs.custid = od.custid);
```

* * * 
## 데이터 조작어 - 삽입, 수정, 삭제

### INSERT문 
INSERT 문은 테이블에 새로운 투플을 삽입하는 명령어이다.
```
INSERT INTO 테이블이름[(속성리스트)] 
	VALUES (값 리스트);
```

- Book 테이블이 새로운 도서 '스포츠 의학'을 삽입하시오. 스포츠 의학은 한솔의학서적에서 출간했으며 가격은 90,000원이다.
```
INSERT INTO Book(bookid, bookname, publisher, price)
	VALUES (11, '스포츠 의학', '한솔의학서적', 90000);
```

> 결과를 확인하기 위해서는 'SELECT \* FROM Book' 명령을 실행해야 한다.

- 새로운 투플을 삽입할 때 속성의 이름은 생략할 수 있다. 이때 데이터의 입력 순서는 속성의 순서와 일치해야 한다.
```
INSERT INTO Book
	VALUES (12, '스포츠 의학', '한솔의학서적', 90000);
```
- 데이터는 항상 속성의 순서대로 입력하지 않아도 된다. 만약 price를 publisher 앞에 입력하고 싶다면 속성의 이름과 데이터의 순서를 바꾸면 된다.
```
INSERT INTO Book(bookid, bookname, price, publisher)
	VALUES (13, '스포츠 의학', 9000, '한솔의학서적');
```

- 만약 몇 개의 속성만 입력해야 한다면 해당되는 속성만 명시하면 된다.
- Book 테이블에 새로운 도서 '스포츠 의학'을 삽입하시오. 스포츠 의학은 한솔의학서적에서 출간했으며 가격은 미정이다.
```
INSERT INTO Book(bookid, bookname, publisher) 
		VALUES (14, '스포츠 의학', '한솔의학서적');
```

- INSERT 문은 SELECT 문을 사용하여 작성할 수도 있다. 이는 한꺼번에 여러 개의 투플을 삽입하는 방법으로 대량 삽입(bulk insert)이라고도 한다. 
- 수입도서 목록(Imported_book)을 Book 테이블에 모두 삽입하시오
```
INSERT INTO Book(bookid, bookname, price, publisher)
		SELECT bookid, bookname, price, publisher
		FROM Imported_book;
```

### UPDATE문
UPDATE 문은 특정 속성 값을 수정하는 명령이다. 
```
UPDATE 테이블이름
SET 속성이름1 = 값1[, 속성이름2 = 값2, ...]
[WHERE <검색조건>]; 
```
> 실습에 앞서 Workbench의 Safe Updates 옵션을 해제해준다.<br>\[Edit\]->\[Preferences\]->\[SQL Editor\]->스크롤바 내림 -> 'Safe Updates' 체크 해제 -> \[OK\]

- Customer 테이블에서 고객번호가 5인 고객의 주소를 '대한민국 부산'으로 변경하시오.
```
SET SQL_SAFE_UPDATES=0; /* Safe Updates 옵션 미 해제 시 실행 */
UPDATE Customer
SET address='대한민국 부산' 
WHERE custid = 5;
```

> 결과를 확인하기 위해서는 'SELECT \* FROM Customer;' 명령을 실행해야 한다.

- UPDATE 문은 다른 테이블의 속성 값을 이용할 수도 있다.
- Book 테이블에서 14번 '스포츠 의학'의 출판사를 Imported_book 테이블의 21번 책의 출판사와 동일하게 변경하시오.
```
UPDATE Book 
SET publisher=(SELECT publisher 
		FROM Imported_book
		WHERE bookid='21')
WHERE bookid='14';
```

### DELETE 문
DELETE 문은 테이블에 있는 기존 투플을 삭제하는 명령이다.
```
DELETE FROM 테이블이름
[WHERE 검색조건];
```

DELETE 문의 \<검색조건\>에 해당하는 투플을 삭제한다. \<검색조건\>이 없으면 모든 투플을 삭제한다.

- Book 테이블에서 도서번호가 11인 도서를 삭제하시오.
```
DELETE FROM Book 
WHERE bookid='11';
```

- 모든 고객을 삭제하시오.
```
DELETE FROM Customer;
```
- 위 SQL문은 실행되지 않는다. 그 이유는 Orders 테이블에서 Customer.custid 속성을 외래키로 참조하고 있기 때문이다. 제약이 해제되지 않으면 데이터 삭제가 중지된다.

> DROP문은 DDL 문으로 테이블의 구조를 삭제한다. 당연히 데이터도 같이 삭제된다. DELETE 문은 DML문으로 테이블의 구조는 그대로 두고 데이터만 삭제한다.