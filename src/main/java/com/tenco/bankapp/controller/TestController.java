package com.tenco.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/temp")
public class TestController {
	
	@GetMapping("temp-test")
	public String tempTest() {
		return "temp";
	}
	@GetMapping("main-page")
	public String mainPage() {
		return "main";
	}
}
