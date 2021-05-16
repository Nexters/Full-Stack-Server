//package fullstack.labelary.controller;
//
//import fullstack.labelary.domain.Member;
//import fullstack.labelary.dto.member.*;
//import fullstack.labelary.service.MemberService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController // Controller & ResponseBody
//@RequiredArgsConstructor
//public class MemberApiController {
//
//    private final MemberService memberService;
//
//    /**
//     * 회원 등록
//     *
//     * @param request RequestBody 정보
//     * @return Response Json 응답
//     */
//    @Operation(summary = "[C] 회원 등록 API")
//    @PostMapping("/api/v1/members")
//    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
//        Member member = new Member();
//        member.setMemEmail(request.getMemEmail());
//        member.setMemName(request.getMemName());
//        member.setMemPassword(request.getMemPassword());
//        member.setMemRegDt(LocalDateTime.now());
//
//        Long id = memberService.join(member);
//        return new CreateMemberResponse(id);
//    }
//
//    /**
//     * 회원 조회 V2 : API 스펙 = DTO
//     *
//     * @return 필요한 Member 정보
//     */
//    @Operation(summary = "[R] 회원 조회 API")
//    @GetMapping("/api/v1/members")
//    public Result<List<ReadMemberRequest>> memberV2() {
//        List<Member> findMembers = memberService.findAllMembers();
//        // 엔티티 -> DTO 반환
//        List<ReadMemberRequest> collect = findMembers.stream()
//                .map(m -> new ReadMemberRequest(m.getMemEmail(), m.getMemName()))
//                .collect(Collectors.toList());
//
//        // Map 감싸서 필요한 정보 추가하기 쉬움(확장성)
//        return new Result<>(collect);
//    }
//
//
//    /**
//     * 회원 정보 수정
//     *
//     * @param idx     수정할 회원 Idx
//     * @param request 수정될 정보
//     * @return Dto 업데이트된 정보
//     */
//    @Operation(summary = "[U] 회원 정보 수정 API")
//    @PostMapping("/api/v1/members/{idx}")
//    public UpdateMemberResponse updateMemberV2(@PathVariable("idx") Long idx,
//                                               @RequestBody @Valid UpdateMemberRequest request) {
//        // 커멘드 쿼리 분리하기
//        // 커멘드
//        memberService.update(idx, request.getMemName());
//        // 쿼리
//        Member findMember = memberService.findOneMember(idx);
//        return new UpdateMemberResponse(findMember.getMemIdx(), findMember.getMemName());
//    }
//
//
//    /**
//     * 라벨 삭제
//     *
//     * @param idx 삭제할 라벨 idx
//     * @return 삭제된 라벨 idx
//     */
//    @Operation(summary = "[D] 회원 삭제 API")
//    @DeleteMapping("api/v1/member/{idx}")
//    public DeleteMemberResponse deleteLabelV2(@PathVariable("idx") Long idx) {
//        memberService.deleteMember(idx);
//        return new DeleteMemberResponse(idx);
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class Result<T> {
//        private T data;
//    }
//}