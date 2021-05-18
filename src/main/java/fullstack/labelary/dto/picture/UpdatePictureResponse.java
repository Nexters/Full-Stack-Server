package fullstack.labelary.dto.picture;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePictureResponse {
    private Long pictureIdx;
    private String originUrl; // 원본 URL
    private String thumbnail; // 썸네일 URL
}
