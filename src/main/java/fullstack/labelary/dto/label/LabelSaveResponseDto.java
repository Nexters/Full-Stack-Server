package fullstack.labelary.dto.label;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 생성한 라벨 정보 Response
 */
@Getter
@NoArgsConstructor
public class LabelSaveResponseDto {
    private Long labelIdx;

    public LabelSaveResponseDto(Long id) {
        this.labelIdx = id;
    }
}
