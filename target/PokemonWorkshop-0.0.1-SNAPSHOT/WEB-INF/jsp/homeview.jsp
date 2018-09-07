<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pokemon Workshop</title>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/homePage.css" />" rel="stylesheet">

<script src="<c:url value="/resources/script/jquery-3.2.1.min.js" />"> </script>
<script src="<c:url value="/resources/script/tabs.js" />"> </script>
<script src="<c:url value="/resources/script/reload.js" />"> </script>

<script>
</script>
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
<div class="infoContainer">
	
	
	<div id="playerImage">
		<br>
		<img src="<c:url value="/resources/pic/player_pic.png" />" height="250" width="100" />
	</div>
	<div class="info">
		<br><br>
		<label> Player Name: ${user.getPlayerName()} </label><br><br><br><br>
	
		<div class="tab">
		  <button class="tablinks" onclick="selectTab(event, 'Dashboard')">Dashboard</button>
		  <button class="tablinks" onclick="selectTab(event, 'Pokedex')">Pokedex</button>
		  <button class="tablinks" onclick="selectTab(event, 'History')">History</button>
		  <button class="tablinks" onclick="selectTab(event, 'Purchased')">Purchased</button>
		</div>
		
		<div id="Dashboard" class="tabcontent" style="display:block">
		  <table class="dashTable">
		  	<tr>
		  		<th> Team </th>
		  		<th> Players </th>
		  		<th> Pokemon </th>
		  		<th> Hunting/Player </th>
		  	</tr>
		  		<c:forEach var="team" items="${teamInfo}">
		  		<tr>
				    <c:forEach var="t" items="${team}">
				        <td> ${t } </td>
				    </c:forEach> 
				</tr>
				</c:forEach>
		  </table>
		</div>
		
		<div id="Pokedex" class="tabcontent">
		  <div class="pokedex">
		  	<c:forEach var="p" items="${pokemonList}">
				<div class="pokemonPic"> 
					<img src="data:image/jpeg;base64,${p.get('POKEMON_IMAGE')}" height="100" width="100" /> 
					<br> ${p.get('POKEMON_NAME') } x ${p.get('HUNT_COUNT') } 
				</div>
			</c:forEach>
		  </div>
		</div>
		
		<div id="History" class="tabcontent">
		  <table class="dashTable">
		  	<tr>
		  		<th> Time </th>
		  		<th> Pokemon </th>
		  		<th> Win </th>
		  	</tr>
		  		<c:forEach var="hunt" items="${huntList}">
			  		<tr>
					    <td> ${hunt.getDateTime() } </td>
					    <td> ${hunt.getPokemonName() } </td>
					    <td> ${hunt.getPlayerWinCount() } - ${hunt.getPokemonWinCount() }</td>
					</tr>
				</c:forEach>
		  </table>
		</div>
		
		<div id="Purchased" class="tabcontent">
		  <table class="dashTable">
		  	<tr>
		  		<th> Time </th>
		  		<th> Item </th>
		  		<th> Price(Baht) </th>
		  		<th> Transaction ID </th>
		  	</tr>
		  		<c:forEach var="purchase" items="${purchaseList}" varStatus="loop">
			  		<tr>
					    <td> ${purchase.getDateTime() } </td>
					    <td> ${itemList[loop.index].getName() } </td>
					    <td> ${itemList[loop.index].getPrice() } </td>
					    <td> ${purchase.getTransactionId() } </td>
					</tr>
				</c:forEach>
		  </table>
		</div>
	</div>
	<br class="clear" />
	
	<div class="button">
		<img src="<c:url value="/resources/pic/pokeball_pic.png" />" height="30" width="35" />
		<a href="fightStandbyMode.html"><button class="btn btn-default"> Play </button></a>
	</div>
</div>

	
	<div id="teamImage">
		<img src="data:image/jpeg;base64,${team.getImage()}" height="70%" width="100%" />
	</div>

</div>
</body>
</html>