package fullstack.labelary.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(of = {"memIdx", "memName", "memEmail"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Label> labels = new ArrayList<>();

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Relation> relations = new ArrayList<>();

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

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
