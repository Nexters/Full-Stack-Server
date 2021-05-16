//package fullstack.labelary.controller.web;
//
//import fullstack.labelary.domain.Member;
//import fullstack.labelary.service.MemberService;
//import fullstack.labelary.web.MemberForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class MemberController {
//    private final MemberService memberService;
//    @GetMapping(value = "/members/new")
//    public String createForm(Model model) {
//        model.addAttribute("memberForm", new MemberForm());
//        return "members/createMemberForm";
//    }
//    @PostMapping(value = "/members/new")
//    public String create(@Valid MemberForm form, BindingResult result) {
//        if (result.hasErrors()) {
//            return "members/createMemberForm";
//        }
//        Member member = new Member();
//        member.setMemEmail(form.getMemEmail());
//        member.setMemName(form.getMemName());
//        member.setMemPassword(form.getMemPassword());
//        memberService.join(member);
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findAllMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }
//
//}