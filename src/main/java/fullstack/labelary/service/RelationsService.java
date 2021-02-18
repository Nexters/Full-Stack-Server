package fullstack.labelary.service;

import fullstack.labelary.domain.Label;
import fullstack.labelary.domain.Relation;
import fullstack.labelary.repository.LabelRepository;
import fullstack.labelary.repository.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RelationsService {

    private final RelationRepository relationRepository;

    /**
     * 라벨링 관계 생성
     *
     * @param relation 관계
     */
    @Transactional
    public void save(Relation relation) {
        RelationRepository.save(relation);
    }

}
