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
- 데이터 정의어는 테이븡릐 구조를 만드는 CREATE 문, 구조를 변경하는 ALTER문, 구조를 삭제하는 DROP문이 있다.

### CREATE 문 


### ALTER 문


### DROP 문

* * * 
## 데이터 조작어(DML)

