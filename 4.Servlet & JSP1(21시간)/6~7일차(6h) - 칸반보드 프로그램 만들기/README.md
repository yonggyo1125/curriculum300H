# 칸반보드 프로그램 만들기

- [칸반보드 샘플소스(완성)](https://github.com/yonggyo1125/kanban_JSP)

## SQL(DDL)

```
CREATE TABLE `kanban`.`works` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('READY', 'PROGRESS', 'DONE') NULL DEFAULT 'READY' COMMENT '작업상태 READY - 준비중, PROGRESS - 진행중, DONE - 완료',
  `workNm` VARCHAR(255) NULL COMMENT '작업내용',
  `regDt` DATETIME NULL DEFAULT NOW() COMMENT '등록일시',
  `modDt` DATETIME NULL COMMENT '수정일시',
  PRIMARY KEY (`id`));
```

## 데이터베이스 연결 : /src/main/java/dao/KanbanDao.java

```java
/**
* Connection 객체 생성하기
* 
* @return
*/
public Connection getConnection() {
	Connection conn = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/kanban?user=kanban&password=aA!123456";
		conn = DriverManager.getConnection(url);
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		
	return conn;
}
```

## DTO 클래스 : /src/main/java/dao/KanbanDto.java

```java
package dto;

import java.time.LocalDateTime;

import constants.Status;

/**
 * KanbanDto (Data Transfer Object)
 * 
 * @author YONGGYO
 *
 */
public class KanbanDto {

	private int id; // 작업 번호
	private Status status; // 작업 상태 
	private String workNm; // 작업 내용
	private LocalDateTime regDt; // 등록일시
	private LocalDateTime modDt; // 수정일시
	
	public KanbanDto() {}
	
	public KanbanDto(Status status, String workNm) {
		super();
		this.status = status;
		this.workNm = workNm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getWorkNm() {
		return workNm;
	}
	
	public void setWorkNm(String workNm) {
		this.workNm = workNm;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}

	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}

	public LocalDateTime getModDt() {
		return modDt;
	}

	public void setModDt(LocalDateTime modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "KanbanDto [id=" + id + ", status=" + status + ", workNm=" + workNm + ", regDt=" + regDt + ", modDt="
				+ modDt + "]";
	}
}
```

## 추가 : /src/main/java/dao/KanbanDao.java

```java

/**
* 작업 등록 
* 
* @param kanban
*/
public void register(KanbanDto kanban) {
	String sql = "INSERT INTO works (status, workNm) VALUES (?,?)";
	try (Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, kanban.getStatus().toString());
		pstmt.setString(2, kanban.getWorkNm().trim());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		
		throw new KanbanException("작업 등록에 실패하였습니다.");
	}
}
```

## 조회

```
/**
* 작업목록 
* 
* @return
*/
public List<KanbanDto> gets() {
		
	return gets(null);
}
	
/**
* 작업 목록 
* 
* @param status 작업 상태
* @return
*/
public List<KanbanDto> gets(Status status) {
	List<KanbanDto> list = null;
		
	StringBuffer sb  = new StringBuffer("SELECT * FROM works ");
	if (status != null) {
		sb.append("WHERE status = ? ");
	}
	sb.append("ORDER BY id DESC");
		
	String sql = sb.toString();
		
	try (Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		ResultSet rs = pstmt.executeQuery();
		list = new ArrayList<KanbanDto>();
		while(rs.next()) {
			KanbanDto kanban = new KanbanDto();
			kanban.setId(rs.getInt("id"));
			kanban.setStatus(Enum.valueOf(Status.class, rs.getString("status")));
			kanban.setWorkNm(rs.getString("workNm"));
			kanban.setRegDt(rs.getTimestamp("regDt").toLocalDateTime());
			Timestamp modDt = rs.getTimestamp("modDt");
			if (modDt != null) {
				kanban.setModDt(modDt.toLocalDateTime());
			}
				
			list.add(kanban);
		}
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
			
	return list;
}
	
/**
* 작업 내역 조회
* 
* @param id
* @return
*/
public KanbanDto get(int id) {
	KanbanDto kanban = new KanbanDto();
	String sql = "SELECT * FROM works WHERE id = ?";
	try (Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			kanban.setId(rs.getInt("id"));
			kanban.setStatus(Enum.valueOf(Status.class, rs.getString("status")));
			kanban.setWorkNm(rs.getString("workNm"));
			kanban.setRegDt(rs.getTimestamp("regDt").toLocalDateTime());
			Timestamp modDt = rs.getTimestamp("modDt");
			if (modDt != null) {
				kanban.setModDt(modDt.toLocalDateTime());
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	return kanban;
}

public KanbanDto get(String id) {
	if (id == null) {
		return null;
	}
		
	return get(Integer.valueOf(id));
}
```

## 수정 : /src/main/java/dao/KanbanDao.java

```java
/**
* 작업 수정
* 
* @param kanban
*/
public void update(KanbanDto kanban) {
	String sql = "UPDATE works SET "
                      + "status = ?, "
                      + "workNm = ?, "
                      + "modDt = ? "
                      + " WHERE id = ?";
	try (Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, kanban.getStatus().toString());
		pstmt.setString(2, kanban.getWorkNm().trim());
		pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
		pstmt.setInt(4, kanban.getId());							
		pstmt.executeUpdate();	
		
	} catch (SQLException e) {
		e.printStackTrace();
			
		throw new KanbanException("수정에 실패하였습니다.");
	}
}
```
## 삭제 : /src/main/java/dao/KanbanDao.java

```java
/**
* 작업 삭제
* 
* @param kanban
*/
public void delete(KanbanDto kanban) {
	delete(kanban.getId());	
}
	
/**
* 작업 삭제
* 
* @param id
*/
public void delete(int id) {
	String sql = "DELETE FROM works WHERE id = ?";
	try (Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
			
	} catch (SQLException e) {
		e.printStackTrace();
			
		throw new KanbanException("삭제에 실패하였습니다.");
	}
}
```

* * * 
# 구현 화면

## PC
![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%B9%B8%EB%B0%98%EB%B3%B4%EB%93%9C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/1.png)

## 모바일 

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%B9%B8%EB%B0%98%EB%B3%B4%EB%93%9C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/2.png)


## 작업 수정
![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/4.Servlet%20%26%20JSP1(21%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%B9%B8%EB%B0%98%EB%B3%B4%EB%93%9C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/3.png)

