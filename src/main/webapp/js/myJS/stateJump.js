/**
 * 
 */
function getParmFormUrl(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){return decodeURI(r[2]);}
    return null;
}

function stateCheck(y){
	if(getParmFormUrl('from') != null){
		switch(y){
			case 1:
				document.getElementById('botton').style.visibility="hidden";
				break;
			case 2:
				var node = document.getElementById('confirm');
				node.parentNode.removeChild(node);
				break;
			case 3:
				document.getElementById('imagebox').removeChild(document.getElementById('image'));
				document.getElementById('auth').style.visibility="hidden";
				$('#other').load('/Drift_wechat/jsp/Orders/FeedbackPreview.jsp'); 
				break;
			case 4:
				var node = document.getElementById('detail');
				node.setAttribute('class', 'weui-form-preview__btn weui-form-preview__btn_default');
				node.setAttribute('id', 'd2');
				node.setAttribute('onclick', 'javascript:query(2);');
				node.innerHTML = '快递查询';
				break;
		}
	}
}