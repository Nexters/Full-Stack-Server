package fullstack.labelary.service;

import fullstack.labelary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원이 존재하는지 검사
     * @param memIdx 회원 번호
     * @return 존재 여부
     */
    public boolean exist(Long memIdx) {
        return memberRepository.existsById(memIdx);
    }

}
