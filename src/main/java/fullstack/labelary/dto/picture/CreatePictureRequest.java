package fullstack.labelary.dto.picture;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 사진 생성 요청
 */
@Data
public class CreatePictureRequest {
    private LocalDateTime pictureDt;    // 사진 생성 시간
    private String thumbnail;           // 썸네일 URL
    private String originUrl;           // 원본 URL
    private Boolean bookmark;           // 즐겨찾기 유무
}