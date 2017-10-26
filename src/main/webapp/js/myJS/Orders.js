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
		document.getElementById('orderId').innerHTML=data[data.length-1].id.slice(-12);
		document.getElementById('startDate').innerHTML=data[data.length-1].startDate;
		document.getElementById('endDate').innerHTML=data[data.length-1].endDate;
		document.getElementById('state').innerHTML=data[data.length-1].state;
		document.getElementById('address').innerHTML=data[data.length-1].address;
		document.getElementById('userName').innerHTML=data[data.length-1].name;
		document.getElementById('userPhone').innerHTML=data[data.length-1].phone;
	}
});