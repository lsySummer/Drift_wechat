/**
 * 
 */
$('Drift_wechat/api/order/delivery',function(json){
	var nickNmae = json.nickName;
	var image = json.image;
	var flag = json.flag;
	if(flag){
		var address = json.address.split(" ");
		var phone = json.phone;
		var name = json.name;
		document.getElementById("name").value = name;
		document.getElementById("phone").value = phone;
		document.getElementById("nickName").value = nickName;
		document.getElementById("address").value = address[0] + address[1] + address[2];
		document.getElementById("address_detail").value = address[3];
	}
});
