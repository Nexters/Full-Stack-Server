package fullstack.labelary.dto.label;

import lombok.Getter;

/**
 * 생성한 라벨 정보 Response
 */
@Getter
public class LabelSaveResponseDto {
    private Long labelIdx;

    public LabelSaveResponseDto(Long id) {
        this.labelIdx = id;
    }
}
