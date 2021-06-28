//package fullstack.labelary.domain;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@ToString(of = {"labelIdx", "labelTitle", "labelDetail"})
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class Label extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long labelIdx;          // 라벨 번호
//    private String labelTitle;      // 라벨 타이틀
//    private String labelDetail;     // 라벨 설명
//    private int labelCount;         // 라벨 사용 횟수
//    private String labelColor;      // 라벨 색
//    private LocalDateTime searchDt; // 검색 시간
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "mem_idx")
//    @ToString.Exclude
//    private Member member;
//
//    @OneToMany(mappedBy = "label")
//    @ToString.Exclude
//    private List<Relation> relations = new ArrayList<>();
//
//    public void setRelations(List<Relation> relations) {
//        this.relations = relations;
//    }
//
//    @Builder
//    public Label(String labelTitle, String labelDetail, String labelColor, Member member) {
//        this.labelTitle = labelTitle;
//        this.labelDetail = labelDetail;
//        this.labelColor = labelColor;
//        if (member != null) {
//            this.setMember(member);
//        }
//    }
//
//    public void update(String labelTitle, String labelDetail, String labelColor) {
//        this.labelTitle = labelTitle;
//        this.labelDetail = labelDetail;
//        this.labelColor = labelColor;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//        member.getLabels().add(this);
//    }
//}