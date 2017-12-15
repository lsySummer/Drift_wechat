/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/get',function(json){
	if(json.before == 'null'){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="</br><p>暂无仪器信息，先去预约吧！</p>";
	}else{
		if(json.enable != "上家已发货"){
			document.getElementById('confirm').style.color="grey";
			document.getElementById('confirm').value="false";
//			document.getElementById('confirm').disabled="true";
		}
		if(json.receive != "暂无物流信息"){
			document.getElementById('d1').style.color="green";
		}
		document.getElementById('previous').innerHTML=json.before;
		document.getElementById('deliveryNum1').innerHTML=json.receive;
		document.getElementById('deviceId1').innerHTML=json.deviceId;
		if(json.after != null){
			if(json.enable != "已确认收货"){
				document.getElementById('detail').style.color="grey";
				document.getElementById('detail').value="false";
//				document.getElementById('detail').disabled="true";
			}
			if(json.send != "暂无物流信息"){
				document.getElementById('d2').style.color="green";
				document.getElementById('detail').style.color="grey";
				document.getElementById('detail').value="false";
			}
			document.getElementById('next').innerHTML=json.after;
			document.getElementById('deliveryNum2').innerHTML=json.send;
			document.getElementById('deviceId2').innerHTML=json.deviceId;
		}else{document.getElementById("item2").style.display="none";}
	}
});

function confirm(){
	if(document.getElementById('confirm').value == "true"){
		$.get('/Drift_wechat/api/delivery/confirm');
		$.toast("收货成功");
		setTimeout("window.location.href='/Drift_wechat/jsp/Delivery.jsp'", 800);
	}else{
		$.toast("暂时无法收货", "forbidden");
	}
}
function detail(){
	if(document.getElementById('detail').value == "true"){
		window.location.href='/Drift_wechat/jsp/DeliveryWrite.jsp';
	}else{
		$.toast("暂时无法填写", "forbidden");
	}
}

function query(x){
  	var delivery = '';
  	if(x == 1){
  		delivery = document.getElementById("deliveryNum1").innerHTML;
  	}else{
  		delivery = document.getElementById("deliveryNum2").innerHTML;
  	}
  	if(delivery == '暂无物流信息'){
  		$.toast("暂无信息，无法查询", "forbidden");
  	}else{
  		$.ajax({
		  url:"http://jisukdcx.market.alicloudapi.com/express/query?number="+ delivery +"&type=auto",
		  type:"get",
		  dataType:"json",
		  data:"hello world",
		  headers: {'Content-Type': 'application/json',
			  		'Authorization':'APPCODE f4726618e0cd48249485fdc44286e869'	
		  },
		  success: function (res) {
		  	if(res.status == '0'){
			  	var temp = res.result;
			    var list = temp.list;
			    var result = "";
			    list.forEach(function(item){
			    	result += '<tr>';
			    	var temp = item.time.split(" ");
			    	result += '<td>' + temp[0] + '<br>' + temp[1] +'</td>';
			    	result += '<td>' + item.status +'</td>';
			    	result += '</tr>';
			    });
			    document.getElementById('lists').innerHTML = result;
		  	}else{
		  		document.getElementById('lists').innerHTML = res.msg;
		  	}
		  }
		});
		$('#about').popup();
  	}
	}
function close(){$.closePopup();}