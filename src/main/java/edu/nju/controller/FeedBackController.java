package edu.nju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.CheckResult;
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/FB")
public class FeedBackController {
	@Autowired
	ReserveService service;
	@RequestMapping(value = "/save")
	@ResponseBody
	public String addFB(@RequestBody List<CheckResult> list,Model model) {
		if(service.addCheckResult(list)){
			return "1"; 
		}
		else{
			return "0"; 
		}
	}
}
