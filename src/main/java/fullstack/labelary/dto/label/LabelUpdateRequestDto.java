package fullstack.labelary.dto.label;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 라벨 이름, 색상 수정
 */
@Getter
@NoArgsConstructor
public class LabelUpdateRequestDto {
    @NotEmpty
    private String labelTitle;
    private String labelDetail;
    @NotEmpty
    private String labelColor;

    @Builder
    public LabelUpdateRequestDto(String labelTitle, String labelDetail, String labelColor) {
        this.labelTitle = labelTitle;
        this.labelDetail = labelDetail;
        this.labelColor = labelColor;
    }
}
