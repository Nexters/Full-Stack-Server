package fullstack.labelary.dto.label;

import fullstack.labelary.domain.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 라벨 데이터 Get Response
 */
@Data
@AllArgsConstructor
public class LabelGetResponseDto {
    private Long labelIdx;
    private String labelTitle;
    private String labelDetail;
    private String labelColor;

    @Builder
    public LabelGetResponseDto(Label entity) {
        this.labelIdx = entity.getLabelIdx();
        this.labelTitle = entity.getLabelTitle();
        this.labelDetail = entity.getLabelDetail();
        this.labelColor = entity.getLabelColor();
    }
}

