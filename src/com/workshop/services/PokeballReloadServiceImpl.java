package com.workshop.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.workshop.controller.MainController;
import com.workshop.dao.PokeballReloadDao;
import com.workshop.dao.PokedexDao;
import com.workshop.model.Pokedex;
import com.workshop.model.User;

public class PokeballReloadServiceImpl implements PokeballReloadService {

	@Autowired
	PokeballReloadDao reload;
	@Autowired
	UserService userService;
	Timer timer = new Timer();

	@Override
	public void insertData(int userId, String timestamp) {
		reload.insertData(userId, timestamp);
	}
	
	@Override
	public void updateData(int userId, String timestamp) {
		reload.updateData(userId, timestamp);
	}

	@Override
	public String getTimestamp(int userId) {
		// TODO Auto-generated method stub
		return reload.getTimestamp(userId);
	}

	@Override
	public void deleteData(int userId) {
		reload.removeData(userId);
	}
	
//	@Override
//	public void setTimer(User user) {
//		timer.cancel();
//		timer.purge();
//		timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			  @Override
//			  public void run() {
//				  System.out.println("run timer");
//			    checkPokeball(user);
//			  }
//		}, MainController.WAIT_TIME*MainController.WAIT_TIME_UNIT*1000, 
//				MainController.WAIT_TIME*MainController.WAIT_TIME_UNIT*1000);
//	}
//	
//	@Override
//	public void stopTimer() {
//		timer.cancel();
//		timer.purge();
//	}
	
	@Override
	public void findDiffTime(HttpSession session, String oldTime, long oldTimeleft) {
		Timestamp oldTimestamp = Timestamp.valueOf(oldTime);
		Timestamp currentTimestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime()));
		long diff = currentTimestamp.getTime() - oldTimestamp.getTime();
		//Find time diff in second
		long diffSeconds = diff / (1000);
		int totalWaitTime = MainController.WAIT_TIME * MainController.WAIT_TIME_UNIT;
		long timeleft;
		if(diffSeconds > totalWaitTime) {
			//not think about oldTimeleft
			timeleft = diffSeconds % totalWaitTime;
			timeleft = totalWaitTime - timeleft;
		}
		else {
			//include oldTimeleft
			if(diffSeconds > oldTimeleft) {
				//Already load pokeball
				diffSeconds = Math.abs(oldTimeleft - diffSeconds);
				timeleft = totalWaitTime - diffSeconds;
			}
			else {
				timeleft = oldTimeleft - diffSeconds;
			}
			
		}
		
		//Update new time 
		currentTimestamp = new Timestamp(currentTimestamp.getTime());
        
		//System.out.println(timeleft + "old: "+oldTimestamp+ " " +oldTimeleft+" now: "+ currentTimestamp);
		
		
		session.setAttribute("timeleft",timeleft);
		session.setAttribute("time",currentTimestamp.toString());
	}
	
	@Override
	public User checkPokeball(User user) {
		System.out.println("check");
		//If current pokeball < max slot
		int userId = user.getId();
		user = userService.getUser(user.getId());
		Timestamp currentTimestamp;
		
		if(user.getCurrentPocketSlot() < user.getMaxPocketSlot()) {
			//Need to start timer
			String t = reload.getTimestamp(userId);
			currentTimestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(Calendar.getInstance().getTime()));
			
			if(t != null){
				Timestamp oldTimestamp = Timestamp.valueOf(t);
				//Once log -> check no. pokeball receive
				long diff = currentTimestamp.getTime() - oldTimestamp.getTime();
				//long diffMinutes = diff / (60 * 1000);
				long diffMinutes = diff / (MainController.WAIT_TIME_UNIT * 1000);
				
				int increasePokeball = (int) (diffMinutes / MainController.WAIT_TIME);
				
//				System.out.println(currentTimestamp.getTime() + "-" + oldTimestamp.getTime());
//				System.out.println(diffMinutes +"/"+MainController.WAIT_TIME + " poke: "+increasePokeball);

				//Update new time into db
				Calendar cal = Calendar.getInstance();
		        cal.setTimeInMillis(oldTimestamp.getTime());
		        cal.add(Calendar.SECOND, increasePokeball*MainController.WAIT_TIME*MainController.WAIT_TIME_UNIT);
		        currentTimestamp = new Timestamp(cal.getTime().getTime());
		        
		        ///////////// Debug
//		        System.out.println(currentTimestamp.toString());
//				
				int currentPocketSlot = user.getCurrentPocketSlot() + increasePokeball;
				if(currentPocketSlot >= user.getMaxPocketSlot()) {
					//More than max -> increase only to max
					user.setCurrentPocketSlot(user.getMaxPocketSlot());
					userService.updateData(user);
					//remove timestamp from db
					deleteData(userId);
				}
				else {
					//Less than max, increase it
					user.setCurrentPocketSlot(user.getCurrentPocketSlot()+increasePokeball);
					userService.updateData(user);
					updateData(userId, currentTimestamp.toString());
				}
			}
			else {
				//Start counting
				currentTimestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(Calendar.getInstance().getTime()));
				insertData(userId, currentTimestamp.toString());
			}
					
		}
		else if(user.getCurrentPocketSlot() >= user.getMaxPocketSlot()) {
			//Already max pokeballs -> remove timestamp of this userid
			deleteData(userId);
		}
		return user;
	}

	@Override
	public String[] findDiffTime(String oldTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTimer(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopTimer() {
		// TODO Auto-generated method stub
		
	}

}
