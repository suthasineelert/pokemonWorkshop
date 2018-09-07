<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pokemon Workshop</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="<c:url value="/resources/script/jquery-3.2.1.min.js" />"> </script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	
  	<script>
const URL = "http://localhost:8080/Workshop/rest/userInfo/username/";
  	
  	$(document).ready(function(){
  		$("#username").on('focusout',function(){
  	  		var username = $(this).val();
  	  	  	      $.get(URL+''+username, function(data, status){
	  	  	  		if (data == "ok"){
		  	  			    $("#usernameField").removeClass("has-error");
		  	  				$("#register").prop('disabled', false);
		  	  				$(".error").hide();
	  	  			    }
	  	  	  		else{
			  	  			$("#usernameField").addClass("has-error");
			  	  			$("#register").prop('disabled', true);
		  	  				$(".error").show();
		  	  				
		  	  			}
  	  	  		});
  		});
  	});
  	</script>
  	<style>
  	.error{
  		display: none;
  		color: red;
  	}
  	
	label{
		color: #000000;
	}
	td{
		padding-right: 5px;
	}
</style>

</head>

<body>
	<div class="loginContainer">
	<!-- Begin # Login Form -->
       <form method="post" name="userLogin" action="login.html">
       <div class="modal-header">
          <h2 class="modal-title center">Login</h2>
        </div>
        <div class="modal-body">
  		<div id="div-login-msg">
            <span class="text-danger">${errMsg }</span>
        </div>
  		<div class="form-group">
		    <label class="control-label">Username</label>
		    <input type="text" class="input form-control" placeholder="Enter Username" name="username" required>
		  </div>
		  <div class="form-group">
		    <label>Password</label>
		    <input type="password" class="input form-control" placeholder="Enter Password" name="password" required>
		  </div>
		</div>
        <div class="modal-footer">
            <div>
                <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
            </div>
    	    <div>
	   	    	<button type="button" class="btn btn-link"  data-toggle="modal" data-target="#myModal">Register</button>
            </div>
        </div>
       </form>
       <!-- End # Login Form -->
       </div>

	
	<!------------- Register ------------------->
  	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
	
	<!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title center">Register</h2>
        </div>
        <div class="modal-body">
          <form class="form-horizontal" method="post" name="userRegister" action="register.html">
		<span class="error">This username has been taken</span>
		<div class="form-group">
		    <label class="control-label col-sm-2" for="username">Username:  </label>
		    <div id="usernameField" class="col-sm-10">
		      <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="password">Password:</label>
		    <div class="col-sm-10"> 
		      <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2">Gender:</label>
		    <div class="col-sm-10"> 
		      <div class="radio">
		        <label class="radio-inline"><input type="radio" name="gender" value="0" required>Male</label>
		        <label class="radio-inline"><input type="radio" name="gender" value="1" required>Female</label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="pName">Player Name:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="pName" name="playerName" placeholder="Enter player name">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2">Team:</label>
		    <div class="col-sm-10"> 
			    <table align="center">
			 
								<c:forEach var="team" items="${teamList}">
					                    <td><img src="data:image/jpeg;base64,${team.getImage()}" height="200" width="150" /></td>         
					     		</c:forEach>
					     		<tr>
						     		<c:forEach var="team" items="${teamList}">
						                    <td class="center"> <label class="normaltxt" for="${team.getId() }">${team.getName()}</label> </td>      
						     		</c:forEach>
					     		</tr>
					     		<tr>
					     			<c:forEach var="team" items="${teamList}">
						                    <td class="center"><input type="radio" name="teamId" id="${team.getId() }" value="${team.getId() }" required> </td>
						     		</c:forEach>
					     		</tr>
			    </table>
		    </div>
		  </div>
		
			  <div class="form-group"> 
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" id="register" class="btn btn-default pull-right">Register</button>
			    </div>
			  </div>
			</form>
        </div>
      </div>
   </div>
   </div>
   <!---------------- End Register  ---------------->
   
</body>
</html>