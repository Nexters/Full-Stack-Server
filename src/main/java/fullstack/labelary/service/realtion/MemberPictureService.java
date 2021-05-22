//package fullstack.labelary.service.realtion;
//
//
//import fullstack.labelary.domain.Member;
//import fullstack.labelary.repository.relation.MemberPictureRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class MemberPictureService {
//    private final MemberPictureRepository memberPictureRepository;
//
//    /**
//     * 회원-사진 관계 등록
//     *
//     * @param member 회원 정보
//     * @return 등록된 회원 idx
//     */
//    @Transactional
//    public Long join(Member member) {
//        return memberRepository.save(member);
//    }
//
//    /**
//     * 전체 회원 조회
//     *s
//     * @return member List
//     */
//    public List<Member> findAllMembers() {
//        return memberRepository.findAll();
//    }
//
//    /**
//     * 회원 Idx 값으로 회원 정보 1건 조회
//     *
//     * @param memberIdx 회원 Idx
//     * @return 회원 정보
//     */
//    public Member findOneMember(Long memberIdx) {
//        return memberRepository.findOne(memberIdx);
//    }
//
//    /**
//     * 회원 이름 업데이트
//     *
//     * @param memIdx  회원 idx
//     * @param memName 변경할 이름
//     */
//    @Transactional
//    public void update(Long memIdx, String memName) {
//        // 변경 감지 데이터 수정
//        Member member = memberRepository.findOne(memIdx);
//        member.setMemName(memName);
//    }
//
//    /**
//     * 회원 삭제
//     *
//     * @param memIdx 삭제할 회원 정보
//     */
//    public void deleteMember(Long memIdx) {
//        memberRepository.deleteMember(memIdx);
//    }
//
//}
