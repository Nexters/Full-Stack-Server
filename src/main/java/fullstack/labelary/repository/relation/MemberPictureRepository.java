package fullstack.labelary.repository.relation;

import fullstack.labelary.domain.MemberPicture;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberPictureRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 회원-사진 정보 생성
     *
     * @param memberPicture 회원-사진 정보
     * @return 생성된 정보 IDX
     */
    public Long save(MemberPicture memberPicture) {
        em.persist(memberPicture);
        return memberPicture.getMemPictureIdx();
    }

    /**
     * 회원-사진 정보 조회
     *
     * @param memIdx 조회할 member idx
     * @param pictureIdx 조회할 picture idx
     * @return 일치하는 정보
     */
    public MemberPicture findOneByTwoIdx(Long memIdx, Long pictureIdx) {
        // TODO 회원 정보로 조회? -> FK 키 연결
        return (MemberPicture) em.createQuery("select m from MemberPicture m where m.memIdx = :memIdx and m.pictureIdx = :pictureIdx", MemberPicture.class)
                .setParameter("pictureIdx", pictureIdx)
                .getResultList();
    }

    /**
     * 모든 회원-사진 관계 조회
     *
     * @return Member List
     */
    public List<MemberPicture> findAll() {
        return em.createQuery("select m from MemberPicture m", MemberPicture.class)
                .getResultList();
    }

    /**
     * 회원-사진 관계 삭제
     *
     * @param memPictureIdx 관 idx
     */
    public void deleteMemberPicture(Long memPictureIdx) {
        MemberPicture member = em.find(MemberPicture.class, memPictureIdx);
        em.remove(member);
    }

}
