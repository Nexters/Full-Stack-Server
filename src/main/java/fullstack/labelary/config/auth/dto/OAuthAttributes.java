package fullstack.labelary.config.auth.dto;

import fullstack.labelary.domain.Member;
import fullstack.labelary.domain.Provider;
import fullstack.labelary.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes; // OAuth2 return Info
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;
    private final Provider provider;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture, Provider provider) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.provider = provider;
    }

    // OAuth 전달 받은 값 구글 규격으로 매핑
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        if (registrationId.equals("facebook")) {
            return ofFacebook("id", attributes);
        }


        if (registrationId.equals("naver")) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofFacebook(String userNameAttributeName,
                                              Map<String, Object> attributes) {
        // 아래의 경우, 이메일을 반환하지 않는다.
        // No Email address on account
        // No confirmed email address on account
        // No verified email address on account
        // https://stackoverflow.com/questions/17532476/facebook-email-field-return-null-even-if-the-email-permission-is-set-and-acce
        String email = (String) attributes.get("email");
        if (email == null) {
            email = attributes.get("name") + "@facebook.com";
        }

        String picture_url = "NULL";

        log.info("### Facebook Attribute : {}", attributes);

        Map<String, Object> picture = (Map<String, Object>) attributes.get("picture");
        if (picture != null) {
            Map<String, Object> picture_data = (Map<String, Object>) picture.get("data");
            picture_url = (String) picture_data.get("url");
        }

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email(email)
                .picture(picture_url)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(Provider.FACEBOOK)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(Provider.GOOGLE)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .provider(Provider.NAVER)
                .build();
    }

    // 새로운 회원인 경우 Entity 생성
    public Member toEntity() {
        return Member.builder()
                .memName(name)
                .memEmail(email)
                .picture(picture)
                .role(Role.GUEST)
                .provider(provider)
                .build();
    }
}