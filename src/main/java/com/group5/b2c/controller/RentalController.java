package com.group5.b2c.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group5.b2c.config.auth.PrincipalMember;
import com.group5.b2c.model.Book;
import com.group5.b2c.service.RentalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/rent/*")
@RequiredArgsConstructor
public class RentalController {
	private final RentalService rentalService;
	
	@GetMapping("request/{num}")
	@ResponseBody
	public String requestBook(@PathVariable long num,@AuthenticationPrincipal PrincipalMember principalMember) {
		rentalService.requestBook(num, principalMember.getMember());
		return "success";
	}
	
	@GetMapping("list")
	public String rentlist(@AuthenticationPrincipal PrincipalMember principalMember, Model model) {
		List<Book> booklist = rentalService.rentlist(principalMember.getMember());
		model.addAttribute("lists", booklist);
		return "/rent/rentlist";
	}
	@GetMapping("acceptrent/{num}")
	@ResponseBody
	public String acceptrent(@PathVariable long num) {
		rentalService.acceptrent(num);
		return "success";
	}
	
}
