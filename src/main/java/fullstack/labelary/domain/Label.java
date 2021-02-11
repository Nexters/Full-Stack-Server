package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Label {

    @Id @GeneratedValue
    @Column(name = "label_idx")
    private Long labelIdx;          // 라벨 번호
    private String labelTitle;      // 라벨 타이틀
    private String labelDetail;     // 라벨 설명
    private int labelCount;         // 라벨 사용 횟수
    private LocalDateTime labelDt;  // 라벨 생성 시간
    private String labelColor;      // 라벨 색
    private LocalDateTime searchDt; // 검색 시간

    @OneToMany(mappedBy = "label")
    private List<Relation> relation = new ArrayList<>();

}
