package edu.nju.controller;

import java.util.Date;

import edu.nju.service.ReserveService;
import edu.nju.utils.Utility;

public class MyThread extends Thread{
	ReserveService service;

	public MyThread(ReserveService service){
		this.service= service;
	}
	
	
	public void run(){
		System.out.println(service.makeOrder("000000005fa5ec9f015fae298e3b0007", "oRTgpwQkDZKxGFvNnfKpJLWvxsyw", 0, Utility.getSpecifiedDayAfter(new Date(),3)));
	}
}
