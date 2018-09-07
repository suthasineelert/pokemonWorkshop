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
<link href="<c:url value="/resources/css/fightResult.css" />" rel="stylesheet">

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
	
	<div class="result">
		<h1> Round # ${round }</h1>
		<label id="pokeWin"> Win ${pokemonWinCount }</label>
		<label id="playerWin"> Win ${playerWinCount }</label>
		<br class="clear">
	</div>
	
	<div class="fightInfoContainer">
		<img src="<c:url value="data:image/jpeg;base64,${pokemon.getImage() }" />" height="150" width="100" />
		<img src="<c:url value="/resources/pic/${pokemonAttack }_pic.png" />" height="70" width="70" />
		<label class="red" style="font-weight: bold;"> VS </label>
		<img src="<c:url value="/resources/pic/${playerAttack }_pic.png" />" height="70" width="70" />
		<img src="<c:url value="/resources/pic/player_pic.png" />" height="200" width="100" />
	</div>	
	
	<div class="choice">
		<a href="skipBattle.html"><button class="btn btn-default"> Skip </button></a>
		<a href="fightMode.html"><button class="btn btn-default"> Play </button></a>
	</div>

</div>

</body>
</html>