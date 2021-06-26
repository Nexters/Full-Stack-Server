//package fullstack.labelary.dto.label;
//
//import fullstack.labelary.domain.Label;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//
///**
// * 라벨 데이터 Get Response
// */
//@Getter
//@NoArgsConstructor
//public class LabelGetResponseDto {
//    private Long labelIdx;
//    private String labelTitle;
//    private String labelDetail;
//    private String labelColor;
//
//    @Builder
//    public LabelGetResponseDto(Label entity) {
//        this.labelIdx = entity.getLabelIdx();
//        this.labelTitle = entity.getLabelTitle();
//        this.labelDetail = entity.getLabelDetail();
//        this.labelColor = entity.getLabelColor();
//    }
//}
//
