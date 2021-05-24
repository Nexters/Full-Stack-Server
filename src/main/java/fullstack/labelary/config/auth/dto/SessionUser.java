package fullstack.labelary.config.auth.dto;

import fullstack.labelary.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private final String name;
    private final String email;
    private final String picture;

    public SessionUser(Member member) {
        this.name = member.getMemName();
        this.email = member.getMemEmail();
        this.picture = member.getPicture();
    }
}