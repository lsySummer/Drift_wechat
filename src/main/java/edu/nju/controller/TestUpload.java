package edu.nju.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pic")
public class TestUpload {
	
	@RequestMapping(value = "/upload")
	public void getMap1(String editorValue) {
		System.out.println(editorValue);
	}	
}
