package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LoginDto;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	@GetMapping("/login") 
	public String form(Model model) {
		LoginDto loginDto = new LoginDto();
		model.addAttribute("loginDto", loginDto);
		
		return "member/login";
	}
	
	@PostMapping("/login")
	public String process(@Valid LoginDto loginDto, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "member/login";
		}
		
		// 로그인 처리 
		
		return "redirect:/";
	}
}
