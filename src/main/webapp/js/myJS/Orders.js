/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/get',function(json){
	if(json == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else{
		document.getElementById('orderId').innerHTML=json.orderId;
		document.getElementById('startDate').innerHTML=json.startDate;
		document.getElementById('endDate').innerHTML=json.endDate;
		document.getElementById('state').innerHTML=json.state;
		document.getElementById('address').innerHTML=json.address;
		document.getElementById('userName').innerHTML=json.address;
		document.getElementById('userPhone').innerHTML=json.address;
	}
});