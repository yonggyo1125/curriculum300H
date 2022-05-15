package day11.exception;

public class IDFormatTest {
	private String userID;
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) throws IDFormatException {
		if (userID == null) {
			// 강제로 예외 발생
			throw new IDFormatException("아이디는 null일 수 없습니다.");
		} else if (userID.length() > 0 || userID.length() > 20) {
			// 강제로 예외 발생 
			throw new IDFormatException("아이디는 8자 이상 20자 이하로 쓰세요");
		}
		this.userID = userID;
	}
	
	public static void main(String[] args) {
		IDFormatTest test = new IDFormatTest();
		
		String userID = null; // 아이디 값이 null인 경우
		try {
			test.setUserID(userID); 
		} catch (IDFormatException e) {
			System.out.println(e.getMessage());
		}
		
		userID = "1234567"; // 아이디 값이 8자 이하인 경우
		try {
			test.setUserID(userID);
		} catch (IDFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
