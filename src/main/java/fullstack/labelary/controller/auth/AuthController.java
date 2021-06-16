package fullstack.labelary.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("api/v1/auth")
    public OAuth2User setting(@AuthenticationPrincipal OAuth2User user) {
        return user;
    }
}
