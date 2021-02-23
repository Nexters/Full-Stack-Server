package fullstack.labelary.api;

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
     * 모든 라벨링 조회 V1 : 특정 데이터만 전달하려면 JsonIgnore 처리
     *
     * @return 라벨링 관계 list
     */
    @GetMapping("/api/v1/relations")
    public List<Relation> RelationsV1() {
        return relationService.findAll();
    }

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
                        r.getPicture().getPictureIdx(),
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
        private Long pictureIdx;   // 사진 번호
        private Long labelIdx;     // 라벨 번호
    }