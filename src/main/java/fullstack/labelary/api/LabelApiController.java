package fullstack.labelary.api;

import fullstack.labelary.domain.Label;
import fullstack.labelary.service.LabelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LabelApiController {

    private final LabelService labelService;


    /**
     * 모든 Label 조회
     *
     * @return Label List
     */
    @GetMapping("/api/v1/labels")
    public List<Label> getLabelsV1() {
        return labelService.findLabels();
    }

    /**
     * 라벨 생성 V2
     *
     * @param request RequestBody 정보
     * @return Response Json 응답
     */
    @PostMapping("/api/v2/labels")
    public CreateLabelResponse saveLabelsV2(@RequestBody @Valid CreateLabelRequest request) {
        Label label = new Label();
        label.setLabelTitle(request.getLabelTitle());
        label.setLabelColor(request.getLabelColor());
        label.setLabelDetail(request.getLabelDetail());
        label.setLabelDt(LocalDateTime.now());

        Long idx = labelService.saveLabel(label);
        return new CreateLabelResponse(idx);
    }

    /**
     * 라벨 수정
     *
     * @param idx 라벨 idx
     * @param request 수정할 label 정보
     * @return 라벨 Response
     */
    @PostMapping("/api/v2/labels/{idx}")
    public UpdateLabelResponse updateLabelV2(@PathVariable("idx") Long idx,
                                               @RequestBody @Valid UpdateLabelRequest request){
        labelService.updateLabel(idx, request.getLabelTitle(), request.getLabelColor());
        Label findLabel = labelService.findOne(idx);
        return new UpdateLabelResponse(findLabel.getLabelIdx(), findLabel.getLabelTitle(), findLabel.getLabelColor());
    }


    /**
     * 라벨 삭제
     *
     * @param idx 삭제할 라벨 idx
     * @return 삭제된 라벨 idx
     */
    @DeleteMapping("api/v2/labels/{idx}")
    public DeleteLabelResponse deleteLabelV2(@PathVariable("idx") Long idx) {
        labelService.deleteLabel(idx);
        return new DeleteLabelResponse(idx);
    }

    /**
     * 라벨 생성 요청
     */
    @Data
    static class CreateLabelRequest {

        @NotEmpty
        private String labelTitle;      // 라벨 타이틀

        @NotEmpty
        private String labelColor;      // 라벨 색

        private String labelDetail;     // 라벨 설명
        private int labelCount;         // 라벨 사용 횟수
        private LocalDateTime labelDt;  // 라벨 생성 시간
        private LocalDateTime searchDt; // 검색 시간

    }

    /**
     * 생성한 라벨 정보 Response
     */
    @Data
    static class CreateLabelResponse {
        private Long labelIdx;

        public CreateLabelResponse(Long id) {
            this.labelIdx = id;
        }
    }

    /**
     * 라벨 이름, 색상 수정
     */
    @Data
    static class UpdateLabelRequest {
        @NotEmpty
        private String labelTitle;
        @NotEmpty
        private String labelColor;
    }

    /**
     * 수정 라벨 데이터 Response
     */
    @Data
    @AllArgsConstructor
    static class UpdateLabelResponse {
        private Long labelIdx;
        private String labelTitle;
        private String labelColor;
    }

    /**
    * 라벨 데이터 Response
    */
    @Data
    @AllArgsConstructor
    static class DeleteLabelResponse {
        private Long labelIdx;
    }
}