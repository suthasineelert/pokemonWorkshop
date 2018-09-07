const URL = "http://localhost:8080/Workshop/rest/userInfo/username/";
  	
  	$(document).ready(function(){
  		$("#username").on('focusout',function(){
  	  		var username = $(this).val();
  	  		alert(username);
  	  		        
  	  	  	      $.get(URL+''+username, function(data, status){
  	  	  			var list = data == null ? [] : (data instanceof Array ? data : [data]);

  	  	  		$.each(list, function(index, user) {
  	  	  			alert(user.id);
  	  	  		if (user.id === undefined){
	  	  			    $("#usernameField").removeClass("has-error");
	  	  				$(".error").hide();
  	  			    }
  	  	  		else{
	  	  				alert("dup");
		  	  			$("#usernameField").addClass("has-error");
	  	  				$(".error").show();
	  	  				
	  	  			}
  	  			});
  	  	  		
  	  	  			if(data == null){
  	  	  				$("#usernameField").removeClass("has-error");
	  	  				$(".error").hide();
  	  	  			}
  	  	  			else{
  	  	  				alert("dup");
  	  	  			$("#usernameField").addClass("has-error");
	  	  				$(".error").show();
  	  	  				
  	  	  			}
  	  	  		});
  	  		    });
  	});