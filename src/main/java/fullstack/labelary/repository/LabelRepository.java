package fullstack.labelary.repository;

import fullstack.labelary.domain.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LabelRepository {

    private final EntityManager em;

    /**
     * 라벨 생성하기
     *
     * @param label 라벨 정보
     * @return 라벨 Idx
     */
    public long save(Label label) {
        // JPA 저장되기 전까지 idx == null
        if (label.getLabelIdx() == null) {
            // 새로 생성
            em.persist(label);
        } else {
            // 이건 update 같이 동작 함
            em.merge(label);
        }
        return label.getLabelIdx();
    }

    /**
     * 라벨 idx 매칭 조회하기
     *
     * @param idx 조회할 라벨 id
     * @return Label Object
     */
    public Label findOne(Long idx) {
        return em.find(Label.class, idx);
    }

    /**
     * 모든 라벨 조회
     *
     * @return 라벨 List
     */
    public List<Label> findAll() {
        return em.createQuery("select l from Label l", Label.class)
                .getResultList();
    }

    /**
     * idx 일치 라벨 삭제
     *
     * @param idx 삭제할 라벨 idx
     */
    public void delete(Long idx) {
        Label toDeleteLabel = em.find(Label.class, idx);
        em.remove(toDeleteLabel);
    }


}
