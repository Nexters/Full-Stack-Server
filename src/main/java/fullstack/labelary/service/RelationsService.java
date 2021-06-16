package fullstack.labelary.service;

import fullstack.labelary.domain.Relation;
import fullstack.labelary.repository.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RelationsService {

    private final RelationRepository relationRepository;

    /**
     * 라벨링 관계 생성(사진 1 : 라벨 n)
     *
     * @param relation 관계
     */
    @Transactional
    public void save(Relation relation) {
        relationRepository.save(relation);
    }

    /**
     * 모든 라벨링 관계 찾기(Admin 사용)
     *
     * @return relation 리스트
     */
    public List<Relation> findAll() {
        return relationRepository.findAll();
    }

    /**
     * 회원 Idx 값으로 전체 라벨링 조회
     *
     * @param memberIdx 회원 Idx
     * @return 라벨링 관계 리스트
     */
    public List<Relation> findByMember(Long memberIdx) {
        return relationRepository.findByMember(memberIdx);
    }

    /**
     * 사진에 대한 라벨 업데이트
     *
     * @param memIdx  회원 idx
     * @param pictureIdx 변경할 이름
     * @param labelIdxList 변경할 Label List
     */
    @Transactional
    public void update(Long memIdx, Long pictureIdx, List<Long> labelIdxList) {
        // 변경 감지 데이터 수정
        // 회원&사진 idx 일치하는 Label List 불러오기
        boolean isExist = false;
        Relation relation = new Relation();
        List<Relation> relationList = relationRepository.findLabelList(memIdx, pictureIdx);
        for (Relation r : relationList) {
            for (Long labelIdx : labelIdxList) {
                // 이미 존재하는 라벨인 경우 유지
                if (r.getLabelIdx().equals(labelIdx)) {
                    isExist = true;
                    break;
                }
            }
            // 존재하지 않는 라벨이라면
            if(!isExist) {
                // 기존에 없는 라벨 값일 경우 - 추가
                relation.setRelationDt(LocalDateTime.now());
                relation.setMemIdx(memIdx);
                relation.setPictureIdx(pictureIdx);
                relation.setLabelIdx(r.getLabelIdx());
                relationRepository.save(relation);
            }
            // Todo API 성능 비교해보기
            // 삭제 로직 추가 후 사진에 대한 라벨 전체 삭제 후 재 저장 로직 구현
        }
    }

}
