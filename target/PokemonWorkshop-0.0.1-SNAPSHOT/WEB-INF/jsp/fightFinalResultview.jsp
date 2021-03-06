<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pokemon Workshop</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/fightMain.css" />" rel="stylesheet">

<script src="<c:url value="/resources/script/jquery-3.2.1.min.js" />"> </script>
<script src="<c:url value="/resources/script/reload.js" />"> </script>
<script>

</script>
<style>
.resultWrapper{
	width: 300px;
	float:left;
}
.image{
	display: inline-block;
}
.resultContainer{
width: 60%;
margin: 0 auto;
	text-align: center;
}
</style>
</head>

<body>
<div class="mainContainer">
	<div class="statusContainer">
		<input id="userId" type="hidden" value="${user.getId() }" />
		<input id="timeleft" type="hidden" value=${timeleft } />
		<a href="homepage.html"><img class="whiteBg" src="<c:url value="/resources/pic/home_pic.png" />" height="30" width="35" /></a>
		<a href="shop.html"><img class="whiteBg" src="<c:url value="/resources/pic/shop_pic2.png" />" height="30" width="35" /></a>
		<img src="<c:url value="/resources/pic/pokeball_pic.png" />" height="30" width="35" />	<label id="noPokeball"> ${user.getCurrentPocketSlot()} </label>
		<div class="progress">
		  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${exp}"
		  aria-valuemin="0" aria-valuemax="100" style="width:${exp}%">
		    ${exp}%
		  </div>
		</div>
		<label> Lv. ${user.getCurrentLevel() }</label>
		<a href="logout.html" id="logoutButton"> <button class="btn btn-default"> Logout </button></a>
		<br class="clear">
	</div>
	
	<br><br><br>
	<div class="resultContainer">
	<div class="resultWrapper">
		<div class="fightInfoContainer">
			<h1> ${msg }</h1>
			<label class="resultScore">  ${playerWinCount } - ${pokemonWinCount }</label>
		</div>	
		
		<div class="choice">
			<img src="<c:url value="/resources/pic/pokeball_pic.png" />" height="30" width="35" />
			<a href="fightStandbyMode.html"><button class="btn btn-default"> Play </button></a>
		</div>
	</div>

	<div class="image">
		<c:choose>
		    <c:when test="${img == 'player_pic.png' }">
		        <img src="<c:url value="/resources/pic/${img}" />" height="150" width="100" />
		    </c:when>    
		    <c:otherwise>
		        <img src=${img } height="150" width="100" />
		    </c:otherwise>
		</c:choose>	
	</div>
		<br class="clear">
	</div>

</div>

</body>
</html>