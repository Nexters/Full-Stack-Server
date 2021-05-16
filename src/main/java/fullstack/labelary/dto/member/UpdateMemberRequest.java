package fullstack.labelary.dto.member;

import lombok.Data;

/**
 * 회원 수정 : 이름만 수정하도록 개발
 */
@Data
public class UpdateMemberRequest {
    private String memName;

}