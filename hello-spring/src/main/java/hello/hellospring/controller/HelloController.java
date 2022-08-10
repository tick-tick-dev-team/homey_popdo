package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	//정적 콘텐츠
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "Spring!!!" );
		return "hello";
	}
	
	// MVC 패턴 
	@GetMapping("hello-mvc")
	public String hellMvc(@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name" , name);
		return "hello-template";
	}
	
	// Strign 문자 반환
	@GetMapping("hello-string")
	@ResponseBody
	public String hellString(@RequestParam(value="name") String name) {
		return "hello" + name; // html을 찾지 않고 그냥 string 문자를 그냥 페이지 바로 넘겨줌
	}

	// API - json 객체 반환
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello(); // 객체 생성시 기본적으로 json 으로 만들어줌
		hello.setName(name);
		return hello; // json 방식으로 리턴
	}
	
	static class Hello {
		private String name;
		
		// 단축키 Ctrl + space바 
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
	}

}
