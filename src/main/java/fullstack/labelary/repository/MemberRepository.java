package fullstack.labelary.repository;

import fullstack.labelary.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 회원 생성
     *
     * @param member 저장될 정보
     * @return 생성된 Member IDX
     */
    public Long save(Member member) {
        em.persist(member);
        return member.getMemIdx();
    }

    /**
     * 회원 idx 통한 조회
     *
     * @param id 조회할 member idx
     * @return 일치하는 Member 정보
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * 모든 회원 조회
     *
     * @return Member List
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 이름으로 회원 조회
     *
     * @param name 회원 이름
     * @return Member List
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.memName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
