package fullstack.labelary.dto.relation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 스크린샷 라벨링 생성 요청
 */
@Getter
@NoArgsConstructor
class RelationSaveRequest {

    @NotEmpty
    private Long memIdx;          // 회원 번호
    @NotEmpty
    private Long pictureIdx;      // 사진 번호
    @NotEmpty
    private List<Long> labelIdx;  // 라벨 번호 list

    @Builder
    public RelationSaveRequest(Long memIdx, Long pictureIdx, List<Long> labelIdx) {
        this.memIdx = memIdx;
        this.pictureIdx = pictureIdx;
        this.labelIdx = labelIdx;
    }
}