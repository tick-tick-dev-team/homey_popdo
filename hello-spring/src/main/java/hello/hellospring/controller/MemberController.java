package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;

	// 생성자 단축키 Alt + Shift + s , O 
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		System.out.println("회원가입 이름" + member.getName());
		
		memberService.join(member);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMember();
		model.addAttribute("members", members);
		return "members/memberList";
	}


}
