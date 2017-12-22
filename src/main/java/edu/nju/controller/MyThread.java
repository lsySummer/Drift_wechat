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
		System.out.println(service.makeOrder( "oRTgpwQkDZKxGFvNnfKpJLWvxsyw", 0, Utility.getSpecifiedDayAfter(new Date(),3)));
	}
}
