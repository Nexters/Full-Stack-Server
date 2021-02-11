package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Picture {

    @Id @GeneratedValue
    @Column(name = "picture_idx")
    private Long pictureIdx;            // 사진 번호
    private int pictureCount;           // 사진 본 횟수
    private LocalDateTime pictureDt;    // 사진 생성 시간
    private LocalDateTime lastSearched; // 마지막 조회 시간
    private String thumbnail;           // 썸네일 URL
    private String originUrl;           // 원본 URL

    @OneToMany(mappedBy = "picture")
    private List<Relation> relation = new ArrayList<>();

}
