package edu.nju.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/manage")
public class ManageController {
	@Autowired
	ManageService manageService;
	
	@Autowired
	ReserveService reserveService;
	
	@RequestMapping(value = "/addDevice")
	public String toAddDevice(HttpSession session,Model model) {
		//String filePath = ManageController.class.getClassLoader().getResource("../province.txt").getPath();
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "jsp/Manage/addDevice";
	}
	
	@RequestMapping(value = "/index")
	public String getIndex(HttpSession session,Model model) {
		List<DeviceVO> deviceList = manageService.getDevices();
		model.addAttribute("deviceList", deviceList);
		return "jsp/Manage/DeviceList";
	}
	
	@RequestMapping(value = "/deviceList")
	public String getDeviceList(HttpSession session,Model model) {
		List<DeviceVO> deviceList = manageService.getDevices();
		model.addAttribute("deviceList", deviceList);
		return "jsp/Manage/DeviceList";
	}
	
	@RequestMapping(value = "/orderList")
	public String getOrderList(HttpSession session,Model model) {
		List<OrderVO> orderList = manageService.getOrders();
		model.addAttribute("orderList", orderList);
		return "jsp/Manage/OrderList";
	}
	
	@RequestMapping(value = "/companySend")
	public String companySend(HttpSession session,Model model) {
		List<OrderVO> orderList = reserveService.getCompanySend();
		model.addAttribute("orderList", orderList);
		return "jsp/Manage/companySend";
	}
	
	@RequestMapping(value = "/deliveryNum")
	public void writeDeliveryNum(String orderId, String deliveryNum, HttpSession session, HttpServletResponse response) {
		reserveService.companySend(orderId, deliveryNum);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/companyReceive")
	public String companyRevice(String orderId, HttpSession session,Model model) {
		List<OrderVO> orderList = reserveService.getCompanyReceive();
		model.addAttribute("orderList", orderList);
		return "jsp/Manage/companyReceive";
	}
	
	@RequestMapping(value = "/receiveConfirm")
	public void reviceConfirm(String orderId, HttpSession session, HttpServletResponse response) {
		reserveService.companyReceive(orderId);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/modify")
	public void modifyOrder(String order, HttpServletResponse response) {
		List<JSONObject> data = new ArrayList<JSONObject>();
//		result.put("test1", "2017-11-14");
//		result.put("test2", "2017-11-13");
		//调用service
		for(Map.Entry<DeviceVO, Date> entry : manageService.getAvailableDevice(order).entrySet()){
			JSONObject result=new JSONObject();
			result.put("id", entry.getKey().getId());
			result.put("number", entry.getKey().getNumber());
			result.put("date", entry.getValue().toString());
			data.add(result);
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/confirm")
	public void confirm(String orderId, String deviceNumber, String deviceId, String date,HttpServletResponse response) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		Date startDate=sdf.parse(date);
		
		//调用service
		manageService.updateOrder(orderId, deviceNumber, deviceId, startDate);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
