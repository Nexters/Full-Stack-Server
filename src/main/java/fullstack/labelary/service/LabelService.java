//package fullstack.labelary.service;
//
//import fullstack.labelary.domain.Label;
//import fullstack.labelary.dto.label.LabelGetResponseDto;
//import fullstack.labelary.dto.label.LabelSaveRequestDto;
//import fullstack.labelary.dto.label.LabelUpdateRequestDto;
//import fullstack.labelary.repository.LabelRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class LabelService {
//
//    private final LabelRepository labelRepository;
//
//    /**
//     * 라벨 등록
//     * @return 등록된 Label idx
//     */
//    @Transactional
//    public Long saveLabel(LabelSaveRequestDto request) {
//        return labelRepository.save(request.toEntity()).getLabelIdx();
//    }
//
//    /**
//     * 모든 라벨 조회
//     * @return 라벨 List
//     */
//    public Page<Label> findLabels(Pageable pageable) {
//        return labelRepository.findAll(pageable);
//    }
//
//    /**
//     * Idx 일치하는 라벨 1건 조회
//     *
//     * @param labelIdx 라벨 Idx
//     * @return 일치하는 라벨
//     */
//    public LabelGetResponseDto findById(Long labelIdx) {
//        Label entity = labelRepository.findById(labelIdx)
//                .orElseThrow(() -> new IllegalArgumentException("해당 라벨이 없습니다. id = " + labelIdx));
//        return new LabelGetResponseDto(entity);
//    }
//
//    /**
//     * 라벨 제목, 설명, 색 업데이트
//     */
//    @Transactional
//    public void updateLabel(Long labelIdx, LabelUpdateRequestDto request) {
//        Label label = labelRepository.findById(labelIdx)
//                .orElseThrow(() -> new IllegalStateException("해당 라벨이 없습니다. id = " + labelIdx));
//        label.update(request.getLabelTitle(), request.getLabelDetail(), request.getLabelColor());
//    }
//
//    /**
//     * 라벨 삭제
//     *
//     * @param labelIdx 라벨 Idx
//     */
//    public void deleteLabel(Long labelIdx) {
//        labelRepository.deleteById(labelIdx);
//    }
//
//    /**
//     * 라벨 존재 여부
//     * @param labelIdx 라벨 Idx
//     * @return 라벨 존재 여부
//     */
//    public boolean exist(Long labelIdx) {
//        return labelRepository.existsById(labelIdx);
//    }
//
//
//}
