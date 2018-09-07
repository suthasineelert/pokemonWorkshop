
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pokemon Workshop</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>

<body>
	<div class="registerContainer">
		<h1>
			Register
		</h1>
		<form class="form-horizontal" method="post" name="userRegister" action="register.html">
		
		<div class="form-group">
		    <label class="control-label col-sm-2" for="username">Username:</label>
		    <div class="col-sm-10">
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
						                    <td class="center"> <label for="${team.getId() }">${team.getName()}</label> </td>      
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
		      <button type="submit" class="btn btn-default pull-right">Register</button>
		    </div>
		  </div>
		</form>
	</div>            
</body>
</html>