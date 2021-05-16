package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long memIdx;            // 회원 idx
    private String memEmail;        // 이메일
    private String memPassword;     // 비밀번호
    private String memName;         // 회원 이름
    private String deviceIdx;       // 스마트폰 idx
    private LocalDateTime memRegDt; // 회원가입 일짜

}
