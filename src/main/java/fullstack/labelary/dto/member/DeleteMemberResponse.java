package fullstack.labelary.dto.member;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 회원 데이터 삭제 Response
 */
@Data
@AllArgsConstructor
public class DeleteMemberResponse {
    private Long memIdx;
}
