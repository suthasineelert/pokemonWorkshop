package com.workshop.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.workshop.model.HuntTransaction;
import com.workshop.model.Item;
import com.workshop.model.LoginHistory;
import com.workshop.model.Payment;
import com.workshop.model.Pokemon;
import com.workshop.model.PurchaseTransaction;
import com.workshop.model.Team;
import com.workshop.model.User;
import com.workshop.services.HuntTransactionService;
import com.workshop.services.ItemService;
import com.workshop.services.LoginHistoryService;
import com.workshop.services.PaymentServiceClient;
import com.workshop.services.PokeballReloadService;
import com.workshop.services.PokemonService;
import com.workshop.services.PurchaseTransactionService;
import com.workshop.services.TeamService;
import com.workshop.services.UserService;

@Controller
@SessionAttributes({ "item" })
public class ShopController {

	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	@Autowired
	PurchaseTransactionService purchaseTransactionService;
	@Autowired
	PaymentServiceClient payment;

	@Autowired
	PokeballReloadService reload;

	String transactionId;
	String accountName;
	String msg = "Payment fail";
	private final String VALID_PAYMENT = "success";
	private final String INVALID_PAYMENT = "Payment Fail";

	@RequestMapping("/shop")
	public ModelAndView shopPage(ModelAndView model, HttpSession session) {
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		reload.findDiffTime(session, time, timeleft);

		List<Item> itemList = new ArrayList<Item>();
		itemList = itemService.getItemList();

		model.addObject("itemList", itemList);
		model.setViewName("shopview");
		return model;
	}

	@RequestMapping("/paymentPage")
	public ModelAndView paymentPage(ModelAndView model, HttpSession session, @RequestParam("item") int itemId) {
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		reload.findDiffTime(session, time, timeleft);

		// Get item list from the database
		Item item = itemService.getItem(itemId);

		model.addObject("item", item);
		model.setViewName("paymentview");
		return model;
	}

	@RequestMapping("/paymentValidate")
	public ModelAndView paymentValidation(ModelAndView model, HttpSession session, @ModelAttribute("item") Item item,
			@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") String amount,
			@RequestParam("pin") String pin) {
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		reload.findDiffTime(session, time, timeleft);

		// Validate Payment (correct account no. + enough money in the account)
		DecimalFormat df = new DecimalFormat("#.00");
		double money = Double.parseDouble(amount);
		amount = df.format(money);

		if (validatePayment(item, accountNumber, amount, pin)) {
			// is valid payment
			model.addObject("accountNumber", accountNumber);
			model.addObject("accountName", accountName);
			model.addObject("transactionId", transactionId);
			model.addObject("pin", pin);
			model.addObject("amount", amount);
			model.setViewName("paymentConfirmview");
			return model;
		} else {
			// not valid payment
			model.addObject("msg", msg);
			model.setViewName("paymentFailview");
			return model;
		}
	}

	@RequestMapping("/paymentConfirm")
	public ModelAndView paymentSuccess(ModelAndView model, HttpSession session, @ModelAttribute("item") Item item,
			@RequestParam("transactionId") String transactionId, @RequestParam("accountNumber") String accountNumber,
			@RequestParam("pin") String pin, @RequestParam("amount") String amount) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		reload.findDiffTime(session, time, timeleft);

		// Make payment
		if (!makePayment(accountNumber, pin, amount, transactionId)) {
			// Fail -> redirect to paymentFail page
			model.addObject("msg", msg);
			model.setViewName("paymentFailview");
			return model;
		}

		// Record purchase transaction
		PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
		purchaseTransaction.setUserId(user.getId());
		purchaseTransaction.setItemId(item.getId());
		purchaseTransaction
				.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		purchaseTransaction.setTransactionId(transactionId);
		purchaseTransactionService.insertData(purchaseTransaction);

		// Update no pokeball of user
		reload.checkPokeball(user);
		user = userService.getUser(user.getId());
		user.setCurrentPocketSlot(user.getCurrentPocketSlot() + item.getNoPokeball());
		userService.updateData(user);

		session.setAttribute("user", user);
		model.setViewName("paymentSuccessview");
		return model;
	}

	private boolean validatePayment(Item item, String accountNumber, String amount, String pin) {
		HashMap<String, String> response;
		DecimalFormat df = new DecimalFormat("#.00");

		/*** validate account existance ***/
		// Change string to double for calculating price
		double money = Double.parseDouble(amount);
		double price = Double.parseDouble(item.getPrice());

		// Check amount of payment more than or equal to item price
		if (price > money) {
			// Not enough money!
			msg = "Invalid paid amount";
			return false;
		}
		// Check with web service for correct account info + enough money
		// Inquiry
		response = payment.inquiryAccount(accountNumber, pin, amount);
		if (response.get("resultCode").equalsIgnoreCase("Success")) {
			// Correct account and money
			transactionId = response.get("transactionID");
			accountName = response.get("accountName");
			return true;
		}
		msg = response.get("resultMessage");
		return false;
	}

	private boolean makePayment(String accountNumber, String pin, String amount, String transactionId) {
		HashMap<String, String> response;

		response = payment.makePayment(accountNumber, pin, amount, transactionId);
		if (response.get("resultCode").equalsIgnoreCase("Success")) {
			// Payment success
			return true;
		}
		msg = response.get("resultMessage");
		return false;
	}
}
