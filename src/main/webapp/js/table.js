function tableSort(jqTableObj) {
    jqTableObj.find('thead th').click(
        function(){
            var dataType = $(this).attr('dataType') || 'text';
            var index = jqTableObj.find('thead th').index(this) + 1;
            var arr = [];
            var row = jqTableObj.find('tbody tr');

            $.each(row, function(i){arr[i] = row[i]});

            if($(this).hasClass('current')){
                arr.reverse();
            } else {
                arr.sort(Utils.sortStr(index, dataType))

                jqTableObj.find('thead th').removeClass('current');
                $(this).addClass('current');
            }

            var fragment = document.createDocumentFragment();

            $.each(arr, function(i){
                fragment.appendChild(arr[i]);
            });

            jqTableObj.find('tbody').append(fragment);
			
			var trList = jqTableObj.find("tr");
			for (var i=1;i<trList.length;i++) {
				var tdArr = trList.eq(i).find("td");
				tdArr.eq(0).text(i);
			}
        }
    );    

    var Utils = (function() {
        function sortStr(index, dataType){
            return function(a, b){
                var aText=$(a).find('td:nth-child(' + index + ')').attr('_order') || $(a).find('td:nth-child(' + index + ')').text();
                var bText=$(b).find('td:nth-child(' + index + ')').attr('_order') || $(b).find('td:nth-child(' + index + ')').text();

                if(dataType != 'text'){
                    aText=parseNonText(aText, dataType);
                    bText=parseNonText(bText, dataType);

                    return aText > bText ? -1 : bText > aText ? 1 : 0;
                } else {
                    return aText.localeCompare(bText)
                }
            }
        }

        function parseNonText(data, dataType){
            switch(dataType){
                case 'int':
                    return parseInt(data) || 0
                case 'float':
                    return parseFloat(data) || 0
                default :
                return filterStr(data)
            }
        }

        function filterStr(data){
            if (!data) {
                return 0;
            }

            return parseFloat(data.replace(/^[\$a-zA-z\u4e00-\u9fa5 ]*(.*?)[a-zA-z\u4e00-\u9fa5 ]*$/,'$1'));
        }

        return {'sortStr' : sortStr};
    })();
}