package fullstack.labelary.config.auth;

import fullstack.labelary.config.auth.dto.OAuthAttributes;
import fullstack.labelary.config.auth.dto.SessionUser;
import fullstack.labelary.domain.Member;
import fullstack.labelary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    /**
     * 1. 구글 로그인 버튼 클릭
     * 2. 구글 로그인 화면 -> 로그인 완료
     * 3. code 리턴 (OAuth-Client 라이브러리)
     * 4. AccessToken 요청
     * 5. UserRequest 정보 리턴
     * 6. loadUser 함수 호출
     * 7. {OAuth 프로바이더} 회원 프로필 리턴
     * 8. @AuthenticationPrincipal 어노테이션 생성
     *
     * @param userRequest OAuth 프로바이더에서 내려준 정보
     * @return OAuth User
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth 에서 처리한 데이터를 후처리하는 함수
        // AccessToken 값 확인
        log.debug(">>> OAuth2 AccessToken {}", userRequest.getAccessToken());
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 서비스 구분 id (구글, 네이버, 카카오, 애플 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 로그인 시, PK가 되는 필드 값 받아오기 (구글:"sub", 네이버 카카오 지원x)
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // 소셜 로그인 된 유저 정보 - 객체화
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        // 사용자 정보 등록 or 업데이트
        Member member = saveOrUpdate(attributes);
        // 세션에 사용자 정보 등록
        httpSession.setAttribute("user", new SessionUser(member));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        // 중복 체크하여 저장 혹은 업데이트
        Member user = memberRepository.findByMemEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());
        // DB에 저장 후 사용자 객체 반환
        return memberRepository.save(user);
    }
}