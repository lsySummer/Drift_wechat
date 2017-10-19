/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/delivery',function(json){
	var before = json.before;
	var after = json.after;
	if(before == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else if(after == null){
		document.getElementById('previous').innerHTML=json.previous;
		document.getElementById('deliveryNum1').innerHTML=json.deliveryNum1;
		document.getElementById('deviceId1').innerHTML=json.deliveryDate1;
	}else{
		document.getElementById('next').innerHTML=json.next;
		document.getElementById('deliveryNum2').innerHTML=json.deliveryNum2;
		document.getElementById('deviceId2').innerHTML=json.deliveryDate2;
	}
});

function confirm(){
	$.toptip('确认收货成功', 'success');
	$.get('/Drift_wechat/api/delivery/confirm');
}