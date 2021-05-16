package fullstack.labelary.dto.member;

import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long memIdx;

    public CreateMemberResponse(Long id) {
        this.memIdx = id;
    }
}