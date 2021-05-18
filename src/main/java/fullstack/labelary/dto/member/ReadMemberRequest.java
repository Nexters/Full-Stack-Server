package fullstack.labelary.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadMemberRequest {
    private String memEmail;
    private String memName;
}