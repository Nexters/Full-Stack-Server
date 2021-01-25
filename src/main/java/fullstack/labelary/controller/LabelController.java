package fullstack.labelary.controller;

import fullstack.labelary.repository.Label;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LabelController {
    private Map<String, Label> labelMap;

    @PostConstruct
    public void init() {
        labelMap = new HashMap<String, Label>();
        labelMap.put("1", new Label(1, "Title", "Detail", "0", "20210125"));
        labelMap.put("2", new Label(2, "Title2", "Detail2", "1", "20210126"));
    }

    /**
     * idx 일치 라벨 정보 조회
     *
     * @param id 라벨 idx
     * @return Label 정보
     */
    @GetMapping("/label/{id}")
    public Label getLabelForIdx(@PathVariable("id") String id) {
        return labelMap.get(id);
    }


}
