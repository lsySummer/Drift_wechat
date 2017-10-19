/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/get',function(json){
	console.log(json);
	var data = json.data;
	if(data == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else{
		document.getElementById('orderId').innerHTML=data[0].id;
		document.getElementById('startDate').innerHTML=data[0].startDate;
		document.getElementById('endDate').innerHTML=data[0].endDate;
		document.getElementById('state').innerHTML=data[0].state;
		document.getElementById('address').innerHTML=data[0].address;
		document.getElementById('userName').innerHTML=data[0].name;
		document.getElementById('userPhone').innerHTML=data[0].phone;
	}
});