package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberDelete {
	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 삭제할 데이터의 조건값 준비
		Member member = new Member();
		member.setMemId("user1");
		
		// 3) 데이터의 삭제 -> 반환값은 삭제된 행의 수
		int affectedRows = sqlSession.delete("MemberMapper.deleteItem", member);
		
		// 4) 결과 판별
		System.out.printf("%d개의 데이터 삭제됨%n", affectedRows);
		
		// 5) 변경사항 저장 및 DB 접속 해제
		// 데이터의 추가, 수정, 삭제는 항상 Transaction 처리되므로 commit()을 해야 최종 반영된다.
		sqlSession.commit();
		sqlSession.close();
	}
}
