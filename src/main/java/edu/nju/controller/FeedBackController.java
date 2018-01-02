package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/FB")
public class FeedBackController {
	@RequestMapping(value = "/show")
	public String companyRevice(@RequestBody List<Object> jqlist, HttpSession session,Model model) {
		model.addAttribute("jqlist", jqlist);
		return "jsp/Manage/companyReceive";
	}
}
