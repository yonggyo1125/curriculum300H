package repository;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.*;

import entity.Member;

public class MemberDao {
	
	public void execute() {
		// [엔티티 매니저 팩토리] - 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exam");
		
		// [엔티티 매니저] - 생성
		EntityManager em = emf.createEntityManager();
		
		// [트랜잭션] 획득
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			logic(em);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	public void logic(EntityManager em) {
		Member member = new Member();
		member.setMemId("user1");
		member.setMemNm("이름");
		
		// 등록
		em.persist(member);
		
		// 수정
		member.setMemNm("이름( 수정)");
		
		// 한 건 조회
		Member findMember = em.find(Member.class, member.getMemNo());
		System.out.println(findMember.getMemId() + ", " + findMember.getMemNm());
		
		// 목록 조회
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size=" + members.size());
		
		// 삭제
		em.remove(member);
	}
}
