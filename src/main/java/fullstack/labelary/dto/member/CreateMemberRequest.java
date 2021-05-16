package fullstack.labelary.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CreateMemberRequest {
    @NotEmpty
    private String memEmail;
    @NotEmpty
    private String memPassword;
    @NotEmpty
    private String memName;

    private String deviceIdx;
    private LocalDateTime memRegDt;
}

