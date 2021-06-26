//package fullstack.labelary.dto.label;
//
//
//import fullstack.labelary.domain.Label;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.NotEmpty;
//
///**
// * 라벨 생성 요청
// */
//@Getter
//@NoArgsConstructor
//public class LabelSaveRequestDto {
//
//    @NotEmpty
//    private String labelTitle;      // 라벨 타이틀
//    @NotEmpty
//    private String labelColor;      // 라벨 색
//    private String labelDetail;     // 라벨 설명
//    @NotEmpty
//    private long memIdx;
//
//    @Builder
//    public LabelSaveRequestDto(String labelTitle, String labelColor, String labelDetail, long memIdx) {
//        this.labelTitle = labelTitle;
//        this.labelColor = labelColor;
//        this.labelDetail = labelDetail;
//        this.memIdx = memIdx;
//    }
//
//    public Label toEntity() {
//        return Label.builder()
//                .labelTitle(labelTitle)
//                .labelColor(labelColor)
//                .labelDetail(labelDetail)
//                .build();
//    }
//
//}