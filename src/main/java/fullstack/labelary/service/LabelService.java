package fullstack.labelary.service;

import fullstack.labelary.domain.Label;
import fullstack.labelary.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    /**
     * 라벨 등록
     *
     * @param label 라벨 정보
     * @return 등록된 Label idx
     */
    @Transactional
    public Long saveLabel(Label label) {
        validateDuplicateLabel(label);
        labelRepository.save(label);
        return label.getLabelIdx();
    }

    /**
     * 모든 라벨 조회
     *
     * @return 라벨 List
     */
    public List<Label> findLabels() {
        return labelRepository.findAll();
    }

    /**
     * Idx 일치하는 라벨 1건 조회
     *
     * @param labelIdx 라벨 Idx
     * @return 일치하는 라벨
     */
    public Label findOne(Long labelIdx) {
        return labelRepository.findOne(labelIdx);
    }

    /**
     * 라벨 이름 업데이트
     *
     * @param labelIdx 라벨 idx
     * @param labelTitle 변경할 라벨 이름
     */
    @Transactional
    public void updateLabel(Long labelIdx, String labelTitle, String labelColor) {
        // 변경 감지 데이터 수정
        Label label = labelRepository.findOne(labelIdx);
        label.setLabelTitle(labelTitle);
        label.setLabelColor(labelColor);
    }

    /**
     * 라벨 삭제
     *
     * @param labelIdx 라벨 Idx
     * @return Idx 일치 라벨 삭제 여부
     */
    public void deleteLabel(Long labelIdx) {
        labelRepository.deleteLabel(labelIdx);
    }

    /**
     * 라벨 이름 중복 검증 로직
     *
     * @param label 라벨 정보
     */
    private void validateDuplicateLabel(Label label) {
        // EXCEPTION 처리
        List<Label> findLabels = labelRepository.findByTitle(label.getLabelTitle());
        if (!findLabels.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 라벨입니다.");
        }
    }
}
