package fullstack.labelary.service;

import fullstack.labelary.domain.Member;
import fullstack.labelary.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입실행")
    // @Rollback(false)
    public void 회원가입() {
        // given
        Member member = new Member();
        member.setMemName("YoungJun");

        // when
        Long savedIdx = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savedIdx));
    }
    // @Test(expected = IllegalStateException.class) try catch 삭제 가능
    @Test
    @DisplayName("중복 회원 검사")
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setMemName("Park");

        Member member2 = new Member();
        member2.setMemName("Park2");

        // when
        memberService.join(member1);
        try {
            memberService.join(member2); // Exception 발생
        } catch (IllegalStateException e) {
            // then
            fail("예외 처리가 되지 않았습니다.");
        }

    }
}