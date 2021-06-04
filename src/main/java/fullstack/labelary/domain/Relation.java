package fullstack.labelary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Relation extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "relation_idx")
    private Long relationIdx;   // 관계 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx")
    private Member member = new Member();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "label_idx")
    private Label label = new Label();

    private String picturePath;

    /**
     * 연관관계 편의 메서드
     * @param member 회원 Entity
     */
    public void setMember(Member member) {
        this.member = member;
        member.getRelations().add(this);
    }

    /**
     * 연관관계 편의 메서드
     * @param label 라벨 Entity
     */
    public void setLabel(Label label) {
        this.label = label;
        label.getRelations().add(this);
    }

    @Builder
    public Relation(Member member, Label label, String picturePath) {
        this.member = member;
        this.label = label;
        this.picturePath = picturePath;
    }

}
