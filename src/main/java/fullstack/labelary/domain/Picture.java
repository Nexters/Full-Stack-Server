//package fullstack.labelary.domain;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class Picture extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long pictureIdx;            // 사진 번호
//    private LocalDateTime lastSearched; // 마지막 조회 시간
//    private String thumbnail;           // 썸네일 URL
//    private String originUrl;           // 원본 URL
//    private Boolean bookmark;           // 즐겨찾기 유무
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
//
//
//}
