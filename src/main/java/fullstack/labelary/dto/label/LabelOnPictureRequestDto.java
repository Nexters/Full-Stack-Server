package fullstack.labelary.dto.label;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 라벨 생성 요청
 */
@Getter
@NoArgsConstructor
public class LabelOnPictureRequestDto {

    @NotEmpty
    private Long memIdx; // 회원 idx
    @NotEmpty
    private Long labelIdx; // 라벨 idx
    @NotEmpty
    private String picturePath; // 사진 경로

}
