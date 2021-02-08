package fullstack.labelary.repository;

import fullstack.labelary.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * JPA - 생성
     *
     * @param member 저장될 정보
     * @return 생성된 Member IDX
     */
    public Long save(Member member) {
        em.persist(member);
        return member.getMemIdx();
    }

    /**
     * JPA - 조회
     *
     * @param id 조회할 member idx
     * @return 일치하는 Member 정보
     */
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
