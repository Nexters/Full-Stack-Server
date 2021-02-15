package fullstack.labelary.repository;

import fullstack.labelary.domain.Picture;
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
     * @return 저장된 사진 Idx
     */
    public long save(Relation relation) {
        // JPA 저장되기 전까지 idx == null
        if (relation.getRelationIdx() == null){
            // 새로 생성
            em.persist(relation);
        } else {
            // 이건 update 같이 동작 함
            em.merge(relation);
        }
        return relation.getRelationIdx();
    }

    /**
     * 사진 조회 idx 매칭 조회하기
     *
     * @param idx 조회할 사진 id
     * @return 사진 Object
     */
    public Picture findOne(Long idx) {
        return em.find(Picture.class, idx);
    }

    /**
     * 모든 사진 조회
     *
     * @return 사진 List
     */
    public List<Picture> findAll() {
        return em.createQuery("select p from Picture p", Picture.class)
                .getResultList();
    }

    /**
     * idx 일치 사진 삭제
     *
     * @param idx 삭제할 사진 idx
     */
    public void delete(Long idx) {
        Picture toDeletePicture = em.find(Picture.class, idx);
        em.remove(toDeletePicture);
    }

    // 사진 즐겨찾기
}
