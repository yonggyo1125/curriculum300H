package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import dto.MemberDto;

@Controller
@RequestMapping("/tpl")
public class BasicController {
	
	@GetMapping("/ex01")
	public String ex01(Model model) {
		model.addAttribute("data", "타임리프 예제입니다.");
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(Model model) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemNo(Long.valueOf(1));
		memberDto.setMemId("user1");
		memberDto.setMemNm("이름1");
		memberDto.setRegDt(LocalDateTime.now());
		
		model.addAttribute("memberDto", memberDto);
		return "ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex04";
	}
	
	@GetMapping("/ex05")
	public String ex05(Model model) {
		
		List<MemberDto>memberDtoList  = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemNo(Long.valueOf(i));
			memberDto.setMemId("user" + i);
			memberDto.setMemNm("이름" + i);
			memberDto.setRegDt(LocalDateTime.now());
			
			memberDtoList.add(memberDto);
		}
		
		model.addAttribute("memberDtoList", memberDtoList);
		return "ex05";
	}
	
	@GetMapping("/ex06")
	public String ex06() {
		return "ex06";
	}
	
	@GetMapping("/ex07")
	public String ex07(String param1, String param2, Model model) {
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		return "ex07";
	}
	
	@GetMapping("/ex08")
	public String ex08(Model model) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemNo(Long.valueOf(1));
		memberDto.setMemId("user1");
		memberDto.setMemNm("이름1");
		memberDto.setRegDt(LocalDateTime.now());
		
		model.addAttribute("memberDto", memberDto);
		return "ex08";
	}
	
	@GetMapping("/ex09")
	public String ex09() {
		return "ex09";
	}
}
