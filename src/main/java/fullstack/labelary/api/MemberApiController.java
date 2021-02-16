package fullstack.labelary.api;

import fullstack.labelary.domain.Member;
import fullstack.labelary.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Controller & ResponseBody
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원 조회 V1 : 회원 데이터만 전달하려면 JsonIgnore 처리
     *
     * @return Member List
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findAllMembers();
    }

    /**
     * 회원 조회 V2 : API 스펙 = DTO
     *
     * @return 필요한 Member 정보
     */
    @GetMapping("/api/v2/members")
    public Result<List<MemberDto>> memberV2() {
        List<Member> findMembers = memberService.findAllMembers();
        // 엔티티 -> DTO 반환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getMemEmail(), m.getMemName()))
                .collect(Collectors.toList());

        // Map 감싸서 필요한 정보 추가하기 쉬움(확장성)
        return new Result<>(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memEmail;
        private String memName;
    }
    /**
     * 회원 등록 V1 : 엔티티 노출
     *
     * @param member 회원 정보
     * @return Response Json
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * 회원 등록 V2 : DTO 사용
     *
     * @param request RequestBody 정보
     * @return Response Json 응답
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setMemEmail(request.getMemEmail());
        member.setMemName(request.getMemName());
        member.setMemPassword(request.getMemPassword());
        member.setMemRegDt(LocalDateTime.now());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members/{idx}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("idx") Long idx,
                                               @RequestBody @Valid UpdateMemberRequest request){
        // 커멘드 쿼리 분리하기
        // 커멘드
        memberService.update(idx, request.getMemName());
        // 쿼리
        Member findMember = memberService.findOneMember(idx);
        return new UpdateMemberResponse(findMember.getMemIdx(), findMember.getMemName());
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String memEmail;
        @NotEmpty
        private String memPassword;
        @NotEmpty
        private String memName;

        private String deviceIdx;
        private LocalDateTime memRegDt;
    }

    @Data
    static class CreateMemberResponse {
        private Long memIdx;

        public CreateMemberResponse(Long id) {
            this.memIdx = id;
        }
    }

    /**
     * 회원 수정 : 이름만 수정하도록 개발
     */
    @Data
    static class UpdateMemberRequest {
        private String memName;

    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long memIdx;
        private String memName;
    }
}