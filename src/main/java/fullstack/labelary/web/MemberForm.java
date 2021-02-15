package fullstack.labelary.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이메일은 필수입니다.")
    private String memEmail;    // 이메일

    private String memPassword; // 비밀번호
    private String memName;     // 회원 이름

}