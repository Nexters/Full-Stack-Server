package fullstack.labelary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    UNAUTHORIZED_EXCEPTION("C001", "세션이 만료되었습니다. 다시 로그인 해주세요"),
    NOT_FOUND_EXCEPTION("C002", "존재하지 않습니다"),
    VALIDATION_EXCEPTION("C003", "잘못된 요청입니다"),
    CONFLICT_EXCEPTION("C004", "이미 존재합니다"),
    INTERNAL_SERVER_EXCEPTION("C005", "서버 내부에서 에러가 발생하였습니다"),
    METHOD_NOT_ALLOWED_EXCEPTION("C006", "지원하지 않는 메소드 입니다"),
    BAD_GATEWAY_EXCEPTION("C007", "외부 연동 중 에러가 발생하였습니다"),

    // Label
    NOT_FOUND_LABEL_EXCEPTION("L001", "존재하지 않는 라벨입니다."),

    // Member
    NOT_FOUND_MEMBER_EXCEPTION("M001", "존재하지 않는 회원입니다.");

    private final String code;
    private final String message;

}