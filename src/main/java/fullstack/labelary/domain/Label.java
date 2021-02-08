package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Label {

    @Id @GeneratedValue
    private int labelIdx;       // 라벨 번호
    private String labelTitle;  // 라벨 타이틀
    private String labelDetail; // 라벨 설명
    private String labelCount;  // 라벨 사용 횟수
    private String labelDt;     // 라벨 생성 시간
    private String labelColor;  // 라벨 색

}
