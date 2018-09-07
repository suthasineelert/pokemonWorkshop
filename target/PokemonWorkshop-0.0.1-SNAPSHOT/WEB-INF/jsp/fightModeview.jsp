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
<link href="<c:url value="/resources/css/fightMode.css" />" rel="stylesheet">

<script src="<c:url value="/resources/script/jquery-3.2.1.min.js" />"> </script>
<script src="<c:url value="/resources/script/reload.js" />"> </script>

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
	<br><br>
	<div class="fightInfoContainer">
		<label> Round # ${round } </label>
	</div>	
	<br><br>
	<div class="selectAttackContainer">
	<form method="post" name="userAttack" action="fightResult.html">	
		<div class="cc-selector">
	        <input id="hammer" type="radio" name="attack" value="HAMMER" checked/>
	        <label class="attack-cc hammer" for="hammer"></label>
	        <input id="scissor" type="radio" name="attack" value="SCISSOR" />
	        <label class="attack-cc scissor"for="scissor"></label>
	        <input id="paper" type="radio" name="attack" value="PAPER" />
	        <label class="attack-cc paper"for="paper"></label>
	    </div>
		
		<br><br>
		<div class="choice">
			<a href="skipBattle.html"><input  class="btn btn-default" type="button" value="Skip" /></a>
			<button  class="btn btn-default" id="playbutton" type="submit">Play</button>
		</div>
	</form>
	</div>
</div>
</body>
</html>