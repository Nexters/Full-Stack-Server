package fullstack.labelary.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Label {

    private int labelIdx;       // 라벨 번호
    private String labelTitle;  // 라벨 타이틀
    private String labelDetail; // 라벨 설명
    private String labelCount;  // 라벨 사용 횟수
    private String labelDt;     // 라벨 생성 시간

    /**
     * Label 생성자
     *
     * @param labelIdx      라벨 id
     * @param labelTitle    라벨 제목
     * @param labelDetail   라벨 설명
     * @param labelCount    라벨 선택 개수
     * @param labelDt       라벨 생성 시간
     */
    public Label(int labelIdx, String labelTitle, String labelDetail, String labelCount, String labelDt) {
        this.labelIdx = labelIdx;
        this.labelTitle = labelTitle;
        this.labelDetail = labelDetail;
        this.labelCount = labelCount;
        this.labelDt = labelDt; // Todo 현재 시간으로 받아서 넣기
    }

}
