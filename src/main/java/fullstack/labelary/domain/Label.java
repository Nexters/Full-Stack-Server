package fullstack.labelary.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Label extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelIdx;          // 라벨 번호

    private String labelTitle;      // 라벨 타이틀

    private String labelDetail;     // 라벨 설명

    private int labelCount;         // 라벨 사용 횟수

    private String labelColor;      // 라벨 색

    private LocalDateTime searchDt; // 검색 시간

    @Builder
    public Label(String labelTitle, String labelDetail, String labelColor) {
        this.labelTitle = labelTitle;
        this.labelDetail = labelDetail;
        this.labelColor = labelColor;
    }

    public void update(String labelTitle, String labelDetail, String labelColor) {
        this.labelTitle = labelTitle;
        this.labelDetail = labelDetail;
        this.labelColor = labelColor;
    }
}
