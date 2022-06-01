package kr.codefty.board.member;

public class Member {
	
	private int memNo; // 회원번호 
	private String memId; // 아이디
	private String memNm; // 회원명 
	private String email; // 이메일 
	private String memPw; // 비밀번호
	private String regDt; // 가입일
	private String modDt; // 정보 수정일
	
	public int getMemNo() {
		return memNo;
	}
	
	public void setMemNo(int memNo) {
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	
	public String getRegDt() {
		return regDt;
	}
	
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getModDt() {
		return modDt;
	}
	
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memNm=" + memNm + ", email=" + email + ", memPw="
				+ memPw + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
}
