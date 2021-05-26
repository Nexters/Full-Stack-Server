package fullstack.labelary.controller.web;

import fullstack.labelary.config.auth.dto.SessionUser;
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

import javax.servlet.http.HttpSession;

@Slf4j
@AllArgsConstructor
@Controller
public class AdminController {

    private final HttpSession httpSession;

    LabelService labelService;

    @GetMapping
    public String labelView(Model model,
                            @PageableDefault(page = 0, size = 10) Pageable page) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        log.info("### Label View Page");
        Page<Label> result = labelService.findLabels(page);
        model.addAttribute("labels", result.getContent());
        model.addAttribute("totalCount", result.getTotalElements());
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("allLabels", result);

        return "basic";
    }


    @GetMapping("login")
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
