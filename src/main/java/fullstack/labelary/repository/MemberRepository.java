package fullstack.labelary.repository;

import fullstack.labelary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 이미 등록된 email 인지 검사
     *
     * @param email 이메일
     * @return 일치하는 회원 정보
     */
    Optional<Member> findByMemEmail(String email);
}
