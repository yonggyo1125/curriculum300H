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
|SUM|SUM(\[ALL | DISTINCT\] 속성이름)|SUM(price)|
|AVG|AVG(\[ALL | DISTINCT\] 속성이름)|AVG(price)|
|COUNT|SUM(\[ALL | DISTINCT | \*\] 속성이름)|COUNT(\*)|
|MAX|MAX(\[ALL | DISTINCT\] 속성이름)|MAX(price)|
|MIN|MIN(\[ALL | DISTINCT\] 속성이름)|MIN(price)|


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
	
## 두 개 이상의 테이블에서 SQL 질의
