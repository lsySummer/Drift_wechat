/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/step4',function(json){
	if(json.name != null){
		document.getElementById('next').innerHTML = json.name;
		document.getElementById('phontNum').innerHTML = json.phone;
		document.getElementById('address').innerHTML = json.address;
		if(json.send != "暂无物流信息"){
			document.getElementById('deliveryNum2').innerHTML = json.send;
		}
	}
});

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
  	if(delivery == '下家已经等不及了～'){
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