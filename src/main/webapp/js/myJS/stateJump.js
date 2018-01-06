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
				document.getElementById('confirm').style.visibility="hidden";
				break;
			case 3:
				document.getElementById('auth').style.visibility="hidden";
				break;
			case 4:
				document.getElementById('detail').style.visibility="hidden";
				document.getElementById('d2').style.visibility="visible";
				break;
		}
	}
}