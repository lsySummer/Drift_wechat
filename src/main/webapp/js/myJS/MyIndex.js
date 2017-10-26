/**
 * 
 */
$.getJSON('/Drift_wechat/api/user/getDetail',function(json){
	var nickName = json.nickName;
	var image = json.image;
	var flag = json.flag;
	alert(getParmFormUrl('state'));
	if(getParmFormUrl('state') != null){
		$.toast("请先填写个人信息", "cancel");
		document.getElementById("state").value = "false";
	}
	
	if(flag){
		var address = json.address.split(" ");
		var phone = json.phone;
		var name = json.name;
		$('#deliveryPerson').val(name);
		document.getElementById("phone").value = phone;
		document.getElementById("nickName").innerHTML = nickName;
		document.getElementById("image").src = json.image;
		document.getElementById("city-picker").value = address[0] + " " + address[1] + " " + address[2];		
		document.getElementById("address_detail").value = address[3];
	}
});

function getParmFormUrl(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){return decodeURI(r[2]);}
    return null;
}