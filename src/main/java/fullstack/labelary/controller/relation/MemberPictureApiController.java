//package fullstack.labelary.controller.relation;
//
//import fullstack.labelary.dto.ApiResponse;
//import fullstack.labelary.dto.relation.CreateMemberPictureRequest;
//import fullstack.labelary.dto.relation.MemberPictureResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequiredArgsConstructor
//public class MemberPictureApiController {
//    private final MemberPictureService MemberPictureService;
//
//    @Operation(summary = "내 사진을 등록하는 API")
//    @PostMapping("/api/v1/member/picture")
//    public ApiResponse<MemberPictureResponse> addMemberPicture(@Valid @RequestBody CreateMemberPictureRequest request, @MemberId Long memberId) {
//        return ApiResponse.success(MemberPictureService.addMemberPicture(request, memberId));
//    }
//
//    @Operation(summary = "내 사진들을 조회하는 API")
//    @GetMapping("/api/v1/member/picture")
//    public ApiResponse<List<StockCalculateResponse>> getStocksInfo(@RequestParam StockMarketType type, @MemberId Long memberId) {
//        return ApiResponse.success(MemberPictureRetrieveService.getMemberCurrentStocks(type, memberId));
//    }
//
//    @Operation(summary = "내 사진을 삭제하는 API")
//    @DeleteMapping("/api/v1/member/picture")
//    public ApiResponse<String> deleteMemberPicture(@Valid DeleteMemberPictureRequest request, @MemberId Long memberId) {
//        MemberPictureService.deleteMemberPicture(request, memberId);
//        return ApiResponse.SUCCESS;
//    }
//}
