package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberUpdate {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 변경할 데이터 준비
		Member param = new Member();
		param.setMemId("user1");
		param.setMemNm("사용자1_2");
		param.setEmail("user1_2@test.org");
		
		// 3) 데이터 수정 -> 반환값은 수정된 행의 수
		int affectedRows = sqlSession.update("MemberMapper.updateItem", param);
		
		// 4) 결과 판별
		System.out.printf("%d 개의 데이터 수정됨%n", affectedRows);
		
		// 5) 변경사항 저장 및 DB 접속 해제
		// 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.commit();
		sqlSession.close();
	}
}
