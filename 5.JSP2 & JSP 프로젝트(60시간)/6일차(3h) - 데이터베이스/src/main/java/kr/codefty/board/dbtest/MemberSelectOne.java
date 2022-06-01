package kr.codefty.board.dbtest;

import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberSelectOne {
	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 조회할 데이터 설정
		Member param = new Member();
		param.setMemId("user1");
		
		// 3) 데이터 조회
		Member member = sqlSession.selectOne("MemberMapper.selectItem", param);
		
		// 4)  결과 판별
		if (member == null) {
			System.out.println("조회결과 없음");
		} else {
			System.out.println(member);
		}

		// 5) DB 접속 해제
		sqlSession.close();
	}
}
