const URL = "http://localhost:8080/Workshop/rest/userInfo/";

var interval;
var time;

function reload() {
	var time = $("#timeleft").val();
	var userId = $("#userId").val();
	//alert("in reload");
		$.get(URL+''+userId, function(data, status){
			var list = data == null ? [] : (data instanceof Array ? data : [data]);
			
			$.each(list, function(index, user) {
			    //alert(user.currentPocketSlot + user.maxPocketSlot);
			    $("#noPokeball").text(user.currentPocketSlot);
			});
		});
//		clearInterval(interval);
//		interval = setInterval(reload, normalTime);
}

$( document ).ready(function() {
	var time = $("#timeleft").val();
	reload();
	//alert(time);
	setTimeout(function(){
		reload();
		interval = setInterval(reload, 1*60*1000);
	}, time*1000);
	
});


