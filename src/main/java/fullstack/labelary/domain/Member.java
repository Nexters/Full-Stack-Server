package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "mem_idx")
    private Long memIdx;            // 회원 idx
    private String memEmail;        // 이메일
    private String memPassword;     // 비밀번호
    private String memName;         // 회원 이름
    private String deviceIdx;       // 스마트폰 idx
    private LocalDateTime memRegDt; // 회원가입 일짜

    @OneToMany(mappedBy = "member")
    private List<Relation> relation = new ArrayList<>();
}
