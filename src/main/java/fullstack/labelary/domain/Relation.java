package fullstack.labelary.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Relation {

    @Id @GeneratedValue
    @Column(name = "relation_idx")
    private Long relationIdx;   // 관계 번호
    private LocalDateTime relationDt;  // 관계 생성 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx")
    private Member member = new Member();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_idx")
    private Picture picture = new Picture();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "label_idx")
    private Label label = new Label();
//
//    //==연관관계 편의 메서드==//
//    public void setMember(Member member) {
//        this.member = member;
//        member.getRelation().add(this);
//    }
//
//    //==연관관계 편의 메서드==//
//    public void setPicture(Picture picture) {
//        this.picture = picture;
//        picture.getRelation().add(this);
//    }
//
//    //==연관관계 편의 메서드==//
//    public void setLabel(Label label) {
//        this.label = label;
//        label.getRelation().add(this);
//    }

    //==생성 메서드==//
    public static Relation createRelation (Member member, Picture picture, Label label) {
        Relation relation = new Relation();
        relation.setMember(member);
        relation.setPicture(picture);
        relation.setLabel(label);

        relation.setRelationDt(LocalDateTime.now());

        return relation;
    }
}
