var likeService = (function(){
    
    function getlikeList(param, callback, error){

        var amounts = param.amounts;
        var uid = param.uid;

        $.getJSON(
            "/mypage/likelist/"+amounts+"/"+uid,
            function(data){
                if(callback){
                    //callback(data);
                    callback(data.likeCnt, data.list);
                }
            }).fail(function(xhr, status, err){
                if(error){
                    error();
                }
            });
         }

    function remove(token, header, rvno, callback, error){
        
        $.ajax({
            type:"DELETE",
            url: "/review/"+ rvno,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
            , 
            success: function(deleteresult, status, xhr){
                if(callback){
                    callback(deleteresult);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    }

    function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
            var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd +" "+ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss ].join('');
		}
    }
    

    return {
        getlikeList : getlikeList,
        remove : remove,
        displayTime : displayTime,
    };
})();