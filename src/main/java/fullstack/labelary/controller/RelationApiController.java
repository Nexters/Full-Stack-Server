package fullstack.labelary.controller;

import fullstack.labelary.domain.Relation;
import fullstack.labelary.service.RelationsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RelationApiController {

    // 라벨링 정보(회원, 사진, 라벨)
    private final RelationsService relationService;

    /**
     * 모든 라벨링 관계 조회 (V2) 필요한 API 스펙
     * Todo 세션을 통한 라벨링 정보 조회 방법
     *
     * @return 필요한 라벨링 정보 list
     */
    @GetMapping("/api/v2/relations")
    public Result<List<RelationDto>> getRelations() {
        List<Relation> findRelations = relationService.findAll();
        // 회원번호 확인 (Todo 세션으로 확인하기)
        // 사진번호 확인
        // 엔티티 -> DTO 반환
        List<RelationDto> collect = findRelations.stream()
                .map(r -> new RelationDto(r.getRelationIdx(),
                        r.getMember().getMemIdx(),
                        r.getPicturePath(),
                        r.getLabel().getLabelIdx()))
                .collect(Collectors.toList());

        // Map 감싸서 필요한 정보 추가하기 쉬움(확장성)
        return new Result<>(collect);
    }

    /**
     * API 스펙에 맞는 결과
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    /**
     * Relation
     */
    @Data
    @AllArgsConstructor
    static class RelationDto {
        private Long relationIdx;  // 라벨링 관계 번호
        private Long memIdx;       // 회원 번호
        private String picturePath;   // 사진 번호
        private Long labelIdx;     // 라벨 번호
    }

    /**
     * 라벨링 생성 V2 : DTO 사용(사진 1 : 라벨 n)
     * Todo 앨범에서 라벨 1 : 사진 n 생성 또는 변경 로직 필요
     *
     * @param request RequestBody 정보
     * @return Response Json 응답
     */
    @PostMapping("/api/v2/relations")
    public CreateRelationResponse saveRelationV2(@RequestBody @Valid CreateRelationRequest request) {
        Relation relation = new Relation();
        // Todo 세션 유지 - 회원 번호
        relation.setMemIdx(request.getMemIdx());
        // 사진 번호
        relation.setPictureIdx(request.getPictureIdx());
        relation.setRelationDt(LocalDateTime.now());
        for (Long labelIdx : request.getLabelIdx()) {
            relation.setLabelIdx(labelIdx);
            relationService.save(relation);
        }
        return new CreateRelationResponse(relation.getRelationIdx());
    }

    /**
     * 라벨링 관계 요청 Dto
     */
    @Data
    static class CreateRelationRequest {
        private Long memIdx;       // 회원 번호
        private Long pictureIdx;   // 사진 번호
        private List<Long> labelIdx;     // 라벨 번호 list
    }

    /**
     * 라벨링 응답 Dto
     */
    @Data
    static class CreateRelationResponse {
        private Long relationIdx;

        public CreateRelationResponse(Long id) {
            this.relationIdx = id;
        }
    }

    /**
     * 라벨링 정보 수정
     *
     * @param memIdx 수정할 라벨링 관계 회원 Idx
     * @param request 수정될 라벨링 정보
     * @return Dto 업데이트된 정보
     */
    @PostMapping("/api/v2/relations/{memIdx}")
    public UpdateRelationResponse updateRelationV2(@PathVariable("memIdx") Long memIdx,
                                                                   @RequestBody @Valid UpdateRelationRequest request){
        // 커멘드
        relationService.update(memIdx, request.getPictureIdx(), request.getLabelList());
        // 쿼리
        List<Relation> findRelations = relationService.findByMember(memIdx);
        return new UpdateRelationResponse(memIdx, findRelations);
    }

    /**
     * 라벨링 수정 : 사진에 대한 라벨만 수정
     */
    @Data
    static class UpdateRelationRequest {
        private Long pictureIdx;
        private List<Long> labelList;

    }

    /**
     * 수정 후 응답 값 Dto
     */
    @Data
    @AllArgsConstructor
    static class UpdateRelationResponse {
        private Long memIdx;
        private List<Relation> relations;
    }
}