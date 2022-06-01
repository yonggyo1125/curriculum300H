package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;
import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberInsert {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 저장할 데이터 준비
		Member member = new Member();
		member.setMemId("user1");
		member.setMemNm("사용자1");
		member.setMemPw("123456");
		member.setEmail("user1@test.org");

		// 3) 데이터 저장
		int affectedRows = sqlSession.insert("MemberMapper.insertItem", member);
		
		// 4) 결과 판별
		System.out.printf("%d개의 데이터 저장됨%n", affectedRows);
		
		
		// 신규로 저장된 데이터의 Primary Key는 입력 파라미터로 전달된 빈(beans)에 저장된다.
		System.out.printf("회원번호(memNo) : " + member.getMemNo());
		
		// 5) 변경사항 저장 및 DB 접속 해제
		// 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.commit();
		sqlSession.close();
	}

}
