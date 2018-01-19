/**
 * 
 */
$.getJSON('/Drift_wechat/api/order/step5',function(json){
	var qList = json.qList;
	var qnumList = json.qnumList;
	var html = '';
	qList.forEach(function(Q, index){
		html += '<a href="/Drift_wechat/api/QA/DateSort?qid='+ Q.id +'" class="weui-media-box weui-media-box_appmsg">';
		html += '<div class="weui-media-box__bd">';
		html += '<p class="weui-media-box__title question" align="left">'+ Q.title +'</p>';
		html += '<p class="weui-media-box__desc" align="left">'+ qnumList[index] +'回答</p></div>';
		html += '<div class="weui-media-box__hd">';
		if(Q.picSig != null){
			html += '<img class="weui-media-box__thumb" src="'+ Q.picSig +'"/></div>';
		}else{
			html += '<img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg"/></div>';
		}
	});
	document.getElementById("recommend").innerHTML = html;
});