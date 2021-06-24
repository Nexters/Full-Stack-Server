//package fullstack.labelary.controller;
//
//import fullstack.labelary.domain.Label;
//import fullstack.labelary.dto.label.*;
//import fullstack.labelary.error.exception.EntityNotFoundException;
//import fullstack.labelary.service.LabelService;
//import fullstack.labelary.service.MemberService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//public class LabelApiController {
//
//    private final LabelService labelService;
//    private final MemberService memberService;
//
//    /**
//     * 라벨 생성
//     *
//     * @param request RequestBody 정보
//     * @return Response Json 응답
//     */
//    @Operation(summary = "[C] 라벨 생성 API")
//    @PostMapping("/api/v1/label")
//    public ResponseEntity<LabelSaveResponseDto> saveLabel(@RequestBody @Valid LabelSaveRequestDto request) {
//        log.info("### Save Label Dto: {}", request);
//        // 같은 회원 - 같은 라벨 제목이면 Error 코드 내리기
//        Long idx = labelService.saveLabel(request);
//        LabelSaveResponseDto responseDto = new LabelSaveResponseDto(idx);
//        return ResponseEntity.ok().body(responseDto);
//    }
//
//    /**
//     * idx 일치 라벨 정보 조회
//     *
//     * @param id 라벨 idx
//     * @return Label 정보
//     */
//    @Operation(summary = "[R] 라벨 1개 조회 API")
//    @GetMapping("/api/v1/label/{id}")
//    public ResponseEntity<LabelGetResponseDto> getLabelForIdx(@PathVariable("id") Long id) {
//        try {
//            return ResponseEntity.ok().body(labelService.findById(id));
//        } catch (Exception e) {
//            log.error("### Error Not Found Label Exception : ", e);
//            throw new EntityNotFoundException(String.format("Label ID[%s] Not Found", id));
//        }
//    }
//
//    /**
//     * 라벨 수정
//     *
//     * @param idx 라벨 idx
//     * @param request 수정할 label 정보
//     * @return 생성된 라벨 응답 dto
//     */
//    @Operation(summary = "[U] 라벨 수정 API")
//    @PutMapping("/api/v1/label/{idx}")
//    public ResponseEntity<LabelGetResponseDto> updateLabelV2(@PathVariable("idx") Long idx,
//                              @RequestBody @Valid LabelUpdateRequestDto request){
//        if(!memberService.exist(request.getMemIdx())) {
//            // member idx 존재 하는지 검사
//            log.error("### Member Not Found : {}", request.getMemIdx());
//            throw new EntityNotFoundException("Member Not Found");
//        } else if(!labelService.exist(idx)){
//            // Label idx 존재 하는지 검사
//            log.error("### Label Not Found : {}", request.getLabelTitle());
//            throw new EntityNotFoundException("Label Not Found");
//        } else {
//            labelService.updateLabel(idx, request);
//            return new ResponseEntity<>(labelService.findById(idx), HttpStatus.CREATED);
//        }
//    }
//
//    /**
//     * 모든 Label 조회
//     *
//     * @return Label List
//     */
//    @Operation(summary = "[R] 라벨 리스트 조회 API")
//    @GetMapping("/api/v1/label")
//    public ResponseEntity<List<Label>> getLabelsV1(@PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok().body(labelService.findLabels(pageable).getContent());
//    }
//
//    /**
//     * 라벨 삭제
//     *
//     * @param idx 삭제할 라벨 idx
//     * @return 삭제된 라벨 idx
//     */
//    @Operation(summary = "[D] 라벨 삭제 API")
//    @DeleteMapping("api/v1/label/{idx}")
//    public ResponseEntity<Long> deleteLabelV2(@PathVariable("idx") Long idx) {
//        if (!labelService.exist(idx)) {
//            // Label idx 존재 하는지 검사
//            log.error("### Label Not Found : {}", idx);
//            throw new EntityNotFoundException("Label Not Found");
//        } else {
//            labelService.deleteLabel(idx);
//            return ResponseEntity.ok().body(idx);
//        }
//    }
//}