package entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="MEMBER")
public class Member {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long memNo;
	
	private String memId;
	
	private String memNm;
	
	@CreationTimestamp
	private LocalDateTime regDt;
	
	@UpdateTimestamp
	private LocalDateTime modDt;
	
	public Long getMemNo() {
		return memNo;
	}
	
	public void setMemNo(Long memNo) {
		this.memNo = memNo;
	}
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemNm() {
		return memNm;
	}
	
	public void setMemNm(String memNm) {
		this.memNm = memNm;
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
}