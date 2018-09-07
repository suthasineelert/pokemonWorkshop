package com.workshop.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.workshop.model.HuntTransaction;
import com.workshop.model.Item;
import com.workshop.model.LoginHistory;
import com.workshop.model.Pokemon;
import com.workshop.model.PurchaseTransaction;
import com.workshop.model.Team;
import com.workshop.model.User;
import com.workshop.services.HuntTransactionService;
import com.workshop.services.ItemService;
import com.workshop.services.LoginHistoryService;
import com.workshop.services.PaymentServiceClient;
import com.workshop.services.PokeballReloadService;
import com.workshop.services.PurchaseTransactionService;
import com.workshop.services.TeamService;
import com.workshop.services.UserService;
import com.workshop.services.PaymentServiceClient;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes({ "errMsg" })
public class MainController {

	@Autowired
	TeamService teamService;
	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	@Autowired
	LoginHistoryService loginHistoryService;
	@Autowired
	HuntTransactionService huntTransactionService;
	@Autowired
	PurchaseTransactionService purchaseTransactionService;
	@Autowired
	PokeballReloadService reload;
	@Autowired
	PaymentServiceClient payment;

	public static final int WAIT_TIME = 15;
	public static final int WAIT_TIME_UNIT = 60;

	@RequestMapping("/homepage")
	public ModelAndView homePage(ModelAndView model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		// Check user pokeball
		// reload.checkPokeball(user);

		// Calculate time for reload pokeball
		if (time == null) {
			session.setAttribute("time",
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		} else {
			reload.findDiffTime(session, time, timeleft);
		}

		user = userService.getUser(user.getId());
		int exp = (user.getCurrentExp() * 100) / user.getMaxExp();
		String[][] teamInfo = teamService.getPlayerandPokemonCount();

		// Dashboard
		Team team = teamService.getTeam(user.getTeamId());
		model.addObject("teamInfo", teamInfo);

		// Pokedex
		List<Map<String, Object>> pokemonList = userService.getPokedex(user.getId());
		model.addObject("pokemonList", pokemonList);

		// History
		List<HuntTransaction> hunt = huntTransactionService.getHuntTransactionInfo(user.getId());
		model.addObject("huntList", hunt);

		// Purchase History
		List<PurchaseTransaction> purchase = purchaseTransactionService
				.getPurchaseTransactionListByUserId(user.getId());
		List<Item> itemList = new ArrayList<Item>();
		for (PurchaseTransaction p : purchase) {
			Item item = itemService.getItem(p.getItemId());
			itemList.add(item);
		}
		model.addObject("itemList", itemList);
		model.addObject("purchaseList", purchase);

		// For team image
		model.addObject("team", team);

		// Set and update session attribute
		session.setAttribute("exp", exp);
		session.setAttribute("user", user);

		model.setViewName("homeview");
		return model;
	}

	@RequestMapping("/login")
	public ModelAndView loginHandle(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		// Get user from database
		User u = userService.getUser(username, password);
		ModelAndView model = new ModelAndView();

		if (u != null) {
			// Valid username and password
			// Record login history into the database
			LoginHistory loginHistory = new LoginHistory();
			loginHistory.setUserId(u.getId());
			loginHistory.setLoginDateTime(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			loginHistoryService.insertData(loginHistory);
			loginHistory = loginHistoryService.getLoginHistory(u.getId());

			// Check pokeball of this user
			reload.checkPokeball(u);

			// Set session attribute
			session.setAttribute("islogin", "true");
			session.setAttribute("loginHistory", loginHistory);
			session.setAttribute("user", u);
			session.setAttribute("timeleft", (long) WAIT_TIME * WAIT_TIME_UNIT);

			// Redirect users to Home page
			RedirectView redirect = new RedirectView("/PokemonWorkshop/homepage");
			redirect.setExposeModelAttributes(false);
			model.setView(redirect);
			return model;
		}
		// Not valid username or password
		String errMsg = "Invalid username or password";
		model.addObject("errMsg", errMsg);

		RedirectView redirect = new RedirectView("/PokemonWorkshop/");
		redirect.setExposeModelAttributes(false);
		model.setView(redirect);
		return model;
	}

	@RequestMapping("/logout")
	public RedirectView logout(ModelAndView model, HttpSession session, SessionStatus status) {
		LoginHistory loginHistory = (LoginHistory) session.getAttribute("loginHistory");
		
		// Record logout time
		loginHistory.setLogoutDateTime(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		loginHistoryService.updateData(loginHistory);

		// Invalidate session
		session.invalidate();
		status.setComplete();

		// Redirect to login page
		RedirectView redirect = new RedirectView("/PokemonWorkshop/");
		redirect.setExposeModelAttributes(false);

		return redirect;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loginAndRegisterPage() {
		ModelAndView model = new ModelAndView();

		List<Team> teamList = teamService.getTeamList();
		model.addObject("teamList", teamList);
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerSubmit(ModelAndView model, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("gender") String gender,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam("teamId") int teamId) {
		// Check whether username exist
		User u = userService.getUser(username);
		if (u != null) {
			model.addObject("errMsg", "Username already exist");
		} else {
			// Save all user information into db
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setPlayerName(playerName);
			user.setTeamId(teamId);
			user.setCurrentLevel(1);
			user.setCurrentExp(0);
			user.setCurrentPocketSlot(15);
			user.setMaxExp(100);
			user.setMaxPocketSlot(15);

			userService.insertData(user);

			model.addObject("errMsg", ""); // Clear error message
		}

		RedirectView redirect = new RedirectView("/PokemonWorkshop/");
		redirect.setExposeModelAttributes(false);
		model.setView(redirect);

		return model;
	}
	
	public static void checkTime(HttpSession session) {
		
	}
}
