package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Picture {

    @Id @GeneratedValue
    private Long pictureIdx;            // 사진 번호
    private LocalDateTime pictureDt;    // 사진 생성 시간
    private LocalDateTime lastSearched; // 마지막 조회 시간
    private String thumbnail;           // 썸네일 URL
    private String originUrl;           // 원본 URL
    private Boolean bookmark;           // 즐겨찾기 유무

}
