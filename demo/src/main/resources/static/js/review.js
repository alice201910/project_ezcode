console.log("review module.....");

var reviewService = (function(){
    
    function add(token, header, review, callback, error){
        console.log("add review......");

        $.ajax({
            type:"POST",
            url:'../review/register',
            data: JSON.stringify(review),
            contentType: "application/json; charset=utf-8",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
            , success : function(result, status, xhr){
                if(callback){
                    callback(result);
                    console.log("result : "+result);
                }
            },
            error:function(xhr, status, er){
                if(error){
                    error(er);
                    alert("에러 :" +er);
                }
            }
        });
    }
    
    function getList(param, callback, error){

        var pno = param.pno;
        var page = param.page || 1;

        $.getJSON(
            "/review/pages/"+pno+"/"+page,
            function(data){
                if(callback){
                    //callback(data);
                    callback(data.reviewCnt, data.list);
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

    function update(token, header, review, callback, error){

        console.log("rvno : "+ review.rvno);

        $.ajax({
            type:"PUT",
            url : '/review/'+review.rvno,
            data: JSON.stringify(review),
            contentType: "application/json; charset=utf-8",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
            , 
            success : function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error : function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })
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
    
    function getCnt(pno,callback, error){
        $.ajax({
            url: "/cshop/read?pno="+pno.pno,
            success : function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error : function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })
    }
    return {
        add : add,
        getList : getList,
        remove : remove,
        update : update,
        displayTime : displayTime,
        getCnt : getCnt
    };
})();