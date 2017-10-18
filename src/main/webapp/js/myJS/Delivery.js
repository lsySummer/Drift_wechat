/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/delivery',function(json){
	var item = json.length;
	if(item == 0){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else if(item == 1){
		document.getElementById('previous').innerHTML=json.previous;
		document.getElementById('deliveryNum1').innerHTML=json.deliveryNum1;
		document.getElementById('deliveryDate1').innerHTML=json.deliveryDate1;
	}else{
		document.getElementById('next').innerHTML=json.next;
		document.getElementById('deliveryNum2').innerHTML=json.deliveryNum2;
		document.getElementById('deliveryDate2').innerHTML=json.deliveryDate2;
	}
});