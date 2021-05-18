package fullstack.labelary.dto.picture;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 사진 데이터 Response
 */
@Data
@AllArgsConstructor
public class DeletePictureResponse {
    private Long pictureIdx;
}