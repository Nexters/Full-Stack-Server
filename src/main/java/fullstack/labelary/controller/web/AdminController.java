package fullstack.labelary.controller.web;

import fullstack.labelary.domain.Label;
import fullstack.labelary.service.LabelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class AdminController {

    LabelService labelService;

    @GetMapping
    public String home() {
        log.info("### Welcome Main Page");
        return "basic";
    }

    @GetMapping("admin")
    public String labelView(Model model,
                            @PageableDefault(page = 0, size = 10) Pageable page) {
        log.info("### Label View Page");
        Page<Label> result = labelService.findLabels(page);
        model.addAttribute("labels", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("allLabels", result);

        return "basic";
    }


    @GetMapping("admin/login")
    public String login() {
        log.info("### Login Page");
        return "login";
    }

    @GetMapping("admin/label/save")
    public String labelSave() {
        log.info("### Label Save");
        return "label-save";
    }
}
