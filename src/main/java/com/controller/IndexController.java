package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

	
	@RequestMapping("/freemarker")
	public String login(Model model) {
		List<String> list = new ArrayList<String>();
		list.add("ALEX");
		list.add("ALEX9");
		model.addAttribute("user", list);
		
		model.addAttribute("ALEX", "Right");
		return "alex";
	}
}
