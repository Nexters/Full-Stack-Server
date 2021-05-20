package fullstack.labelary.dto.relation;

import lombok.Data;
/**
 * 회원-사진 생성 요청
 */
@Data
public class CreateMemberPictureRequest {
    private Long memIdx;            // 회원 idx
    private String originUrl;       // 원본 URL
}
