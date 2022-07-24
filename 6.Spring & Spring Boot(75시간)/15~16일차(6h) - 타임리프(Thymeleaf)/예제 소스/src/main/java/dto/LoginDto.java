package dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {
	
	@NotBlank(message="아아디를 입력하세요.")
	private String memId;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memPw;
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
}
