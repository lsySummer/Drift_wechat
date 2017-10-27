package edu.nju.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Device;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.service.ManageService;

@Controller
@RequestMapping(value="/manage")
public class ManageController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/addDevice")
	public String toAddDevice(HttpSession session,Model model) {
		//String filePath = ManageController.class.getClassLoader().getResource("../province.txt").getPath();
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "jsp/AddDevice";
	}
	
	@RequestMapping(value = "/")
	public String getIndex(HttpSession session,Model model) {
		List<DeviceVO> deviceList = manageService.getDevices();
		model.addAttribute("deviceList", deviceList);
		return "jsp/DeviceList";
	}
	
	@RequestMapping(value = "/deviceList")
	public String getDeviceList(HttpSession session,Model model) {
		List<DeviceVO> deviceList = manageService.getDevices();
		model.addAttribute("deviceList", deviceList);
		return "jsp/DeviceList";
	}
	
	@RequestMapping(value = "/orderList")
	public String getOrderList(HttpSession session,Model model) {
		List<OrderVO> orderList = manageService.getOrders();
		model.addAttribute("orderList", orderList);
		return "jsp/OrderList";
	}
	
	@RequestMapping(value = "/addDeviceAction")
	@ResponseBody
	public String addDevice(String number, @RequestParam("areas") String[] areas, String type,Model model) {
		
		List<String> areaList = new ArrayList<String>();		
		for(int i=0;i<areas.length;i++){
			areaList.add(areas[i]);
		}
		Device device =  new Device();
		device.setLoc("company");
		device.setNumber(number);
		device.setQueueNum(0);
		manageService.addDeviceList(device, areaList, Integer.parseInt(type));
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "success";
	}
	
	public static List<String> readFile()
	{   
		String filePath = ManageController.class.getClassLoader().getResource("../province.txt").getPath();
		List<String> provinces = new ArrayList<String>();   
	    try   
	    {       
	        File f = new File(filePath);      
	        if(f.isFile()&&f.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");       
	            BufferedReader reader=new BufferedReader(read);       
	            String line;       
	            while ((line = reader.readLine()) != null)   
	            {        
	            	provinces.add(line);
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }     
	    return provinces;   
	}   
}
