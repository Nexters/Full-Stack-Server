package fullstack.labelary.controller;

import fullstack.labelary.domain.Label;
import fullstack.labelary.dto.label.*;
import fullstack.labelary.service.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LabelApiController {

    private final LabelService labelService;

    /**
     * 라벨 생성
     *
     * @param request RequestBody 정보
     * @return Response Json 응답
     */
    @Operation(summary = "[C] 라벨 생성 API")
    @PostMapping("/api/v1/label")
    public ResponseEntity<LabelSaveResponseDto> saveLabelsV2(@RequestBody @Valid LabelSaveRequestDto request) {
        log.info("### Save Label Dto: {}", request);
        Long idx = labelService.saveLabel(request);
        LabelSaveResponseDto responseDto = new LabelSaveResponseDto(idx);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * idx 일치 라벨 정보 조회
     *
     * @param id 라벨 idx
     * @return Label 정보
     */
    @Operation(summary = "[R] 라벨 1개 조회 API")
    @GetMapping("/api/v1/label/{id}")
    public LabelGetResponseDto getLabelForIdx(@PathVariable("id") Long id) {
        return labelService.findById(id);
    }

    /**
     * 라벨 수정
     *
     * @param idx 라벨 idx
     * @param request 수정할 label 정보
     * @return 라벨 idx
     */
    @Operation(summary = "[U] 라벨 수정 API")
    @PutMapping("/api/v1/label/{idx}")
    public Long updateLabelV2(@PathVariable("idx") Long idx,
                                                @RequestBody @Valid LabelUpdateRequestDto request){
        return labelService.updateLabel(idx, request);
    }

    /**
     * 모든 Label 조회
     *
     * @return Label List
     */
    @Operation(summary = "[R] 라벨 리스트 조회 API")
    @GetMapping("/api/v1/label")
    public List<Label> getLabelsV1(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return labelService.findLabels(pageable).getContent();
    }

    /**
     * 라벨 삭제
     *
     * @param idx 삭제할 라벨 idx
     * @return 삭제된 라벨 idx
     */
    @Operation(summary = "[D] 라벨 삭제 API")
    @DeleteMapping("api/v1/label/{idx}")
    public Long deleteLabelV2(@PathVariable("idx") Long idx) {
        labelService.deleteLabel(idx);
        return idx;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("setting")
    public OAuth2User setting(@AuthenticationPrincipal OAuth2User user) {
        return user;
    }
}