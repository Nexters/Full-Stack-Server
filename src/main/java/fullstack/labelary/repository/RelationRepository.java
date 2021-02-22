package fullstack.labelary.repository;

import fullstack.labelary.domain.Relation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RelationRepository {

    private final EntityManager em;

    /**
     * 라벨링 생성
     *
     * @param relation 관계 엔티
     */
    public void save(Relation relation) {
        // JPA 저장되기 전까지 idx == null
        if (relation.getRelationIdx() == null){
            // 새로 생성
            em.persist(relation);
        } else {
            // 이건 update 같이 동작 함
            em.merge(relation);
        }
    }

    /**
     * 라벨링 조회
     *
     * @param idx 조회할 라벨링 정보
     * @return 사진 Object
     */
    public Relation findOne(Long idx) {
        return em.find(Relation.class, idx);
    }

    /**
     * 모든 라벨링 관계 조회
     *
     * @return 사진 List
     */
    public List<Relation> findAll() {
        return em.createQuery("select r from Relation r", Relation.class)
                .getResultList();
    }

    /**
     * 현재 회원의 모든 라벨링 조회
     *
     * @param idx 조회할 member idx
     * @return 라벨링 관계 list
     */
    public List<Relation> findByMember(Long idx) {
        return em.createQuery("select r from Relation r where r.memIdx = :memIdx", Relation.class)
                .setParameter("memIdx", idx)
                .getResultList();
    }


    /**
     * 회원+사진 idx 일치하는 라벨 리스트
     *
     * @param memIdx 회원 번호
     * @param pictureIdx 사진 번호
     * @return 일치하는 라벨링 리스트
     */
    public List<Relation> findLabelList(Long memIdx, Long pictureIdx) {
        return em.createQuery("select r from Relation r " +
                "where r.memIdx = :memIdx and r.pictureIdx = :pictureIdx", Relation.class)
                .setParameter("memIdx", memIdx)
                .setParameter("pictureIdx", pictureIdx)
                .getResultList();
    }


    /**
     * idx 일치 라벨링 삭제
     * // Todo 사진 삭제 시 관련 라벨링 row 전부 삭제 (사진 기능)
     * // Todo 라벨 삭제 시 관련 라벨링 row 전부 삭제 (앨범 기능)
     *
     * @param idx 삭제할 라벨링 관계 idx
     */
    public void delete(Long idx) {
        Relation toDeleteRelation = em.find(Relation.class, idx);
        em.remove(toDeleteRelation);
    }

}
