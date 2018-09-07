<input id="userId" type="hidden" value="${user.getId() }" />
		<a href="homepage.html"><img class="whiteBg" src="<c:url value="/resources/pic/home_pic.png" />" height="30" width="35" /></a>
		<a href="shop.html"><img class="whiteBg" src="<c:url value="/resources/pic/shop_pic2.png" />" height="30" width="35" /></a>
		<img src="<c:url value="/resources/pic/pokeball_pic.png" />" height="30" width="35" />	<label id="noPokeball"> ${user.getCurrentPocketSlot()} </label>
		<div id="maxExp"> <div id="currentExp" style="width: ${exp}%"></div> </div>
		<label> ${user.getCurrentLevel() }</label>
		<a href="logout.html" id="logoutButton"> <button class="btn btn-default"> Logout </button></a>
		<br class="clear">