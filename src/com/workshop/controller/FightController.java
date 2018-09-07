package com.workshop.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.workshop.enumerator.AttackType;
import com.workshop.enumerator.FightStatus;
import com.workshop.model.HuntTransaction;
import com.workshop.model.Pokedex;
import com.workshop.model.Pokemon;
import com.workshop.model.User;
import com.workshop.services.HuntTransactionService;
import com.workshop.services.PokeballReloadService;
import com.workshop.services.PokedexService;
import com.workshop.services.PokemonService;
import com.workshop.services.UserService;

@Controller
@Scope("session")
@SessionAttributes({ "round", "pokemon", "cp", "pokemonWinCount", "playerWinCount"})
public class FightController {

	AttackType pokemonAttack;
	FightStatus status = FightStatus.STANDBY;
	@Autowired
	UserService userService;
	@Autowired
	PokemonService pokemonService;
	@Autowired
	HuntTransactionService huntTransactionService;
	@Autowired
	PokedexService pokedexService;

	@Autowired
	PokeballReloadService reload;

	private Random rand = new Random();
	private final int TOTAL_ROUND = 3;

	String img = "player_pic.png";
	String msg = "You Win";

	@RequestMapping("/fightStandbyMode")
	public ModelAndView fightStandbyMode(ModelAndView model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");

		// Update user pokeball
		user = reload.checkPokeball(user);
		reload.findDiffTime(session, time, timeleft);

		// Random Pokemon
		Pokemon pokemon = pokemonService.getRandomPokemon();

		// Random CP of each pokemon by min and max
		int cp = rand.nextInt(pokemon.getMaxCP()) + pokemon.getMinCP();

		session.setAttribute("user", user);
		model.addObject("pokemon", pokemon);
		model.addObject("cp", cp);
		model.addObject("round", 0);
		model.addObject("pokemonWinCount", 0);
		model.addObject("playerWinCount", 0);
		model.setViewName("fightStandbyview");
		return model;
	}

	@RequestMapping("/fightMode")
	public ModelAndView fightMode(ModelAndView model, HttpSession session, @ModelAttribute("round") int round,
			@ModelAttribute("pokemonWinCount") int pokemonWinCount,
			@ModelAttribute("playerWinCount") int playerWinCount, @ModelAttribute("pokemon") Pokemon pokemon,
			@ModelAttribute("cp") int cp) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		user = userService.getUser(user.getId());
		reload.findDiffTime(session, time, timeleft);

		// If not having enough pokeball -> redirect to homepage
		// if (round == 0 && user.getCurrentPocketSlot() == 0) {
		// // Change fight status
		// status = FightStatus.STANDBY;
		// model.setViewName("redirect:/homepage.html");
		// return model;
		// }
		// Reduce pokeball of user by 1
		if (round == 0) {
			status = FightStatus.FIGHT;
			user.setCurrentPocketSlot(user.getCurrentPocketSlot() - 1);
			reload.checkPokeball(user);
			if (user.getCurrentPocketSlot() == user.getMaxPocketSlot() - 1) {
				// If pokeball was at max -> start counting reload time
				session.setAttribute("timeleft", (long) MainController.WAIT_TIME * MainController.WAIT_TIME_UNIT);
				session.setAttribute("time",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			}
		}

		//If from fightMode page
		if (status == FightStatus.FIGHT) {
			status = FightStatus.RESULT;

			// If end of battle (end of round 3)
			if (round >= 3) {
				return finalResultCompute(model, session, pokemon, user, cp, pokemonWinCount, playerWinCount);
			}

			// Update user info. (# of Pokeball)
			userService.updateData(user);
			session.setAttribute("user", user);
			model.addObject("round", round + 1);
		}
		//If from finalResult page (refresh)
		if (status == FightStatus.STANDBY) {
			// If end of battle (end of round 3)
			if (round >= 3) {
				return finalResultCompute(model,session, pokemon, user, cp, pokemonWinCount, playerWinCount);
			}
		}

		model.setViewName("fightModeview");
		return model;
	}

	@RequestMapping("/fightResult")
	public ModelAndView fightResult(ModelAndView model, HttpSession session, @ModelAttribute("round") int round,
			@ModelAttribute("pokemonWinCount") int pokemonWinCount, @ModelAttribute("playerWinCount") int playerWinCount,
			@RequestParam("attack") String attack) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");
		reload.findDiffTime(session, time, timeleft);
		
		if (status == FightStatus.RESULT) {
			status = FightStatus.FIGHT;
			// Random pokemon attack
			int n = rand.nextInt(3);

			switch (n) {
			case 0:
				pokemonAttack = AttackType.HAMMER;
				break;
			case 1:
				pokemonAttack = AttackType.SCISSOR;
				break;
			default:
				pokemonAttack = AttackType.PAPER;
			}

			int result = pokemonAttack.checkResult(AttackType.valueOf(attack));
			// Check winner side
			if (result == 1) {
				// Pokemon Win
				model.addObject("pokemonWinCount", pokemonWinCount + 1);
			} else if (result == -1) {
				// Player win
				model.addObject("playerWinCount", playerWinCount + 1);
			}
		}
		model.addObject("pokemonAttack", pokemonAttack.getValue());
		model.addObject("playerAttack", AttackType.valueOf(attack).getValue());
		model.setViewName("fightResultview");
		return model;
	}

	@RequestMapping("/skipBattle")
	public ModelAndView skipBattle(ModelAndView model, HttpSession session,
			@ModelAttribute("pokemon") Pokemon pokemon, @ModelAttribute("pokemonWinCount") int pokemonWinCount,
			@ModelAttribute("playerWinCount") int playerWinCount) {
		User user = (User) session.getAttribute("user");
		String time = (String) session.getAttribute("time");
		long timeleft = (Long) session.getAttribute("timeleft");

		reload.findDiffTime(session, time, timeleft);

		status = FightStatus.STANDBY;
		model.addObject("round", 0);

		RedirectView redirect = new RedirectView("/PokemonWorkshop/fightStandbyMode");
		redirect.setExposeModelAttributes(false);
		model.setView(redirect);
		return model;
	}

	private ModelAndView finalResultCompute(ModelAndView model, HttpSession session, Pokemon pokemon, User user, int cp, int pokemonWinCount,
			int playerWinCount) {
		if (status == FightStatus.RESULT) {
			status = FightStatus.STANDBY;

			// Compute earned EXP
			int earnExp = computeExp(pokemon, cp, pokemonWinCount, playerWinCount);
			int totalExp = user.getCurrentExp() + earnExp;

			// Update user current exp, level, max exp, max pocket slot
			while (totalExp > user.getMaxExp()) {
				// Level Up! -> totalExp - maxExp
				totalExp = totalExp - user.getMaxExp();
				user.setCurrentLevel(user.getCurrentLevel() + 1);
				user.setMaxExp(2 * user.getMaxExp() + (int) Math.ceil(0.1 * user.getMaxExp()));
				user.setMaxPocketSlot(user.getMaxPocketSlot() + (int) Math.ceil(0.15 * user.getMaxPocketSlot()));
			}
			user.setCurrentExp(totalExp);
			userService.updateData(user);

			// Update Hunt Transaction
			HuntTransaction h = new HuntTransaction();
			h.setUserId(user.getId());
			h.setPokemonId(pokemon.getId());
			h.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			h.setEarnExp(earnExp);
			h.setPlayerWinCount(playerWinCount);
			h.setPokemonWinCount(pokemonWinCount);
			huntTransactionService.insertData(h);

			// Update user Pokedex
			if (playerWinCount > pokemonWinCount) {
				// User win -> add Pokemon
				Pokedex pokedex = new Pokedex();
				pokedex.setUserId(user.getId());
				pokedex.setPokemonId(pokemon.getId());
				pokedexService.insertOrUpdateData(pokedex);
			}

			img = "player_pic.png";
			// Show final result
			// Check whether player or pokemon win
			if (pokemonWinCount > playerWinCount) {
				// Pokemon win -> show pokemon image
				img = "data:image/jpeg;base64," + pokemon.getImage();
				msg = "You Lose Na";
			} else if (pokemonWinCount == playerWinCount) {
				// Draw ja
				msg = "Draw Ja";
			} else {
				msg = "You Win";
			}

			int exp = (user.getCurrentExp() * 100) / user.getMaxExp();

			//Update user and exp in session
			session.setAttribute("user", user);
			session.setAttribute("exp", exp);
		}
		model.addObject("img", img);
		model.addObject("msg", msg);
		model.setViewName("fightFinalResultview");
		return model;
	}

	private int computeExp(Pokemon pokemon, int cp, int pokemonWinCount, int playerWinCount) {
		if (pokemonWinCount > playerWinCount) {
			// Pokemon win
			double test = cp * ((double) (TOTAL_ROUND - playerWinCount) / TOTAL_ROUND);
			return (int) test;
		} else {
			// Player win or Draw
			double test = cp * ((double) playerWinCount / TOTAL_ROUND);
			return (int) test;
		}
	}
}
