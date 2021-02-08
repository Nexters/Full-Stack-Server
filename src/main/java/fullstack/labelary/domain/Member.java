package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long memIdx;        // 회원 idx
    private String memName;     // 회원 이름

}
