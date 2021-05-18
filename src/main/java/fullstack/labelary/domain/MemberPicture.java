package fullstack.labelary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class MemberPicture {
    @Id
    @GeneratedValue
    private Long memPictureIdx;     // 멤버 - 사진 idx
    private Long memIdx;            // 회원 idx
    private Long pictureIdx;        // 사진 idx
    private LocalDateTime createDt; // 생성 일짜
}
