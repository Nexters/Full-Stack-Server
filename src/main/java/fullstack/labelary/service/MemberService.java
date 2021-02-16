package fullstack.labelary.service;

import fullstack.labelary.domain.Member;
import fullstack.labelary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // @RequiredArgsConstructor 활용(Lombok)
    // Autowired 없이 final 포함하면 생성자 주입을 자동 생성
    private final MemberRepository memberRepository;

    /**
     * 회원 등록
     *
     * @param member 회원 정보
     * @return 등록된 회원 idx
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getMemIdx();
    }

    /**
     * 전체 회원 조회
     *
     * @return member List
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 Idx 값으로 회원 정보 1건 조회
     *
     * @param memberIdx 회원 Idx
     * @return 회원 정보
     */
    public Member findOneMember(Long memberIdx) {
        return memberRepository.findOne(memberIdx);
    }

    /**
     * 회원 이름 업데이트
     *
     * @param memIdx  회원 idx
     * @param memName 변경할 이름
     */
    @Transactional
    public void update(Long memIdx, String memName) {
        // 변경 감지 데이터 수정
        Member member = memberRepository.findOne(memIdx);
        member.setMemName(memName);
    }

    /**
     * 중복 회원 검증 로직
     *
     * @param member 회원 정보
     */
    private void validateDuplicateMember(Member member) {
        // EXCEPTION 처리
        List<Member> findMembers = memberRepository.findByEmail(member.getMemEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
