package fullstack.labelary.dto.picture;

import lombok.Data;

/**
 * 생성한 사진 정보 Response
 */
@Data
public class CreatePictureResponse {
    private Long pictureIdx;

    public CreatePictureResponse(Long id) {
        this.pictureIdx = id;
    }
}