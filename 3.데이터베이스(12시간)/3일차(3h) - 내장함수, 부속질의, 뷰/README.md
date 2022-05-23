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
			예) LPAD('page 1', 10, '*') -> '\*\*\*\*Page 1"
		</td>
	</tr>
	<tr>
		<td>REPLACE(s1, s2, s3)</td>
		<td>
			대상 문자열의 지정한 문자를 원하는 문자로 변경<br>
			예) REPLACE('AbC', '5, '\*') -> 'AbC\*\*'
		</td>
	</tr>
	<tr>
		<td>RPAD(s,n,c)</td>
		<td>
			대상 문자열의 오른쪽부터 지정한 자리수까지 지정한 문자로 채움<br>
			예) RPAD('AbC', 5, '\*') -> 'AbC\*\*'
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
SELECT SUBSTR(name, 1, 1) '성', COUNT(\*) '원원'
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

* * * 
## 부속질의

### 스칼라 부속질의 - SELECT 부속질의

### 인라인 뷰 - FROM 부속질의

### 중첩질의 - WHERE 부속질의

* * *
## 뷰 

### 뷰의 생성

### 뷰의 수정

### 뷰의 삭제

