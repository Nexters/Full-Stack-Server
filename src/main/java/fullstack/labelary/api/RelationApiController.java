package fullstack.labelary.api;

import fullstack.labelary.domain.Label;
import fullstack.labelary.domain.Member;
import fullstack.labelary.domain.Relation;
import fullstack.labelary.service.LabelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RelationApiController {

    // 라벨링 정보(회원, 사진, 라벨)
    private final RelationsService relationService;

    /**
     * 모든 라벨링 관계 조회
     *
     * @return 필요한 라벨링 정보
     */
    @GetMapping("/api/v2/relations")
    public Result<List<RelationDto>> getRelations() {
        List<Relation> findRelations = relationService.findRelations();
        // 엔티티 -> DTO 반환
        List<RelationDto> collect = findRelations.stream()
                .map(m -> new RelationDto(m.getMemEmail(), m.getMemName()))
                .collect(Collectors.toList());

        // Map 감싸서 필요한 정보 추가하기 쉬움(확장성)
        return new Result<>(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}