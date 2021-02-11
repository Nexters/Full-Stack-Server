package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Relation {

    @Id @GeneratedValue
    @Column(name = "relation_idx")
    private Long relationIdx;   // 관계 번호
    private String relationDt;  // 관계 생성 시간

    @ManyToOne()
    @JoinColumn(name = "mem_idx")
    private Member member = new Member();


    @ManyToOne
    @JoinColumn(name = "picture_idx")
    private Picture picture = new Picture();


    @ManyToOne
    @JoinColumn(name = "label_idx")
    private Label label = new Label();


}
