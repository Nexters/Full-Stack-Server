package fullstack.labelary.dto.label;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 라벨 데이터 Response
 */
@Data
@AllArgsConstructor
public class DeleteLabelResponse {
    private Long labelIdx;
}
