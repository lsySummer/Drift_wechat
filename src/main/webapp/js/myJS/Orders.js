/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/detail',function(json){
	var item = json.length;
	if(item == 0){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}
	document.getElementById('orderId').innerHTML=json.orderId;
	document.getElementById('startDate').innerHTML=json.startDate;
	document.getElementById('endDate').innerHTML=json.endDate;
	document.getElementById('orderId').innerHTML=json.orderId;
	document.getElementById('address').innerHTML=json.address;
});