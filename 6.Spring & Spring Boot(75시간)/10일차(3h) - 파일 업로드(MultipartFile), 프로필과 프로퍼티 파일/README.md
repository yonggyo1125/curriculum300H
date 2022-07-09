# 프로젝트 준비
- 스프링 MVC : 날짜 값 변환, @PathVariable, 컨트롤러 예외 처리 에서 사용한 [예제소스](https://github.com/yonggyo1125/curriculum300H/tree/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/9%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC(%EB%82%A0%EC%A7%9C%20%EA%B0%92%20%EB%B3%80%ED%99%98%2C%20%40PathVariable%2C%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC%20%EC%98%88%EC%99%B8%20%EC%B2%98%EB%A6%AC)/%ED%95%99%EC%8A%B5%20%EC%98%88%EC%A0%9C) 를 이어서 사용
 
# 스프링 파일 업로드(MultipartFile)

* * * 
# 프로필과 프로퍼티 파일

## 프로필

- 개발을 진행하는 동안에는 실제 서비스 목적으로 운영중인 DB를 이용할 수는 없다. 개발하는 동안에는 개발용 DB를 따로 사용하거나 개발 PC에 직접 DB를 설치해서 사용한다. 지금까지의 예제에서 사용한 커넥션 풀 설정을 봐도 아래 코드처럼 로컬 PC에 설치한 MySQL을 사용했다.

```java
@Bean(destroyMethod = "close")
public DataSource dataSource() {
	DataSource ds = new DataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
	ds.setUsername("spring5");
	ds.setPassword("spring5");
	... 생략
	
	return ds;
}
```

- 실제 서비스 환경에서는 웹 서버와 DB 서버가 서로 다른 장비에 설치된 경우가 많다. 개발 환경에서 사용한 DB 계정과 실 서비스 환경에서 사용할 DB 계정이 다른 경우도 흔하다. 즉 개발을 완료한 어플리케이션을 실제 서버에 배포하려면 실 서비스 환경에 맞는 JDBC 연결 정보를 사용해야 한다.

- 실 서비스 장비에 배포하기 전에 설정 정보를 변경하고 배포하는 방법을 사용할 수도 있지만 이 방법은 너무 원시적이다. 이 방법은 실수하기 쉽다. 실 서비스 환경에 맞는 설정으로 수정하는 과정에서 오타를 입력할 수 있고 개발 환경설정을 실 서비스 환경에 배포할 수도 있다. 반대로 실 서비스 정보를 그대로 두고 개발을 진행할 수도 있다.

- 이런 실수를 방지하는 방법은 처음부터 개발 목적 설정과 실 서비스 목적의 설정을 구분해서 작성하는 것이다. 이를 위한 스프링 기능이 프로필(profile)이다.

- 프로필은 논리적인 이름으로서 설정 집합에 프로필을 지정할 수 있다. 스프링 컨테이너는 설정 집합 중에서 지정한 이름을 사용하는 프로필을 선택하고 해당 프로필에 속한 설정을 이용해서 컨테이너를 초기화할 수 있다. 예를 들어 로컬 개발 환경을 위한 DataSource 설정을 "dev" 프로필로 지정하고 실 서비스 환경을 위한 DataSource 설정을 “real" 프로필로 지정한 뒤, "dev" 프로필을 사용해서 스프링 컨테이너를 초기화할 수 있다. 그러면 스프링은 다음과같이 "dev" 프로필에 정의된 빈을 사용하게 된다.





* * *
# 데이터베이스를 서비스 환경과 테스트 환경으로 구분하여 설정하기


