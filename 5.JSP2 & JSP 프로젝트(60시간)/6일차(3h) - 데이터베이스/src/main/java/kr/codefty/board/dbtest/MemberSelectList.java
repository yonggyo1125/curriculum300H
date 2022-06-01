package kr.codefty.board.dbtest;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import kr.codefty.config.mybatis.MyBatisConnectionFactory;
import kr.codefty.board.member.Member;

public class MemberSelectList {

	public static void main(String[] args) {
		// 1) 데이터베이스 접속
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		// 2) 데이터 조회
		List<Member> members = sqlSession.selectList("MemberMapper.selectList");
		
		// 4) 결과 판별
		if (members == null) {
			System.out.println("조회결과 없음");
		} else {
			for(Member member : members) {
				System.out.println(member);
			}
		}
		
		// 5) DB 접속 해제
		sqlSession.close();
	}
}
