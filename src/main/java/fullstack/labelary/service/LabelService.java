package fullstack.labelary.service;

import fullstack.labelary.domain.Label;
import fullstack.labelary.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    /**
     * 라벨 등록
     *
     * @param label 라벨 정보
     */
    @Transactional
    public void saveLabel(Label label) {
        labelRepository.save(label);
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
}
