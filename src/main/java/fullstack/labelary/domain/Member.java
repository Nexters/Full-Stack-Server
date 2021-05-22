package fullstack.labelary.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memIdx; // 회원 idx

    @Column(nullable = false)
    private String memEmail; // 이메일

    @Column(nullable = false)
    private String memName; // 회원 이름

    @Column(nullable = false)
    private String picture; // 회원 프로필사진

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 스프링 시큐리티를 위한 권한 코드

    @Builder
    public Member(String memEmail, String memName, String picture, Role role) {
        this.memEmail = memEmail;
        this.memName = memName;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String memName, String picture) {
        this.memName = memName;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
