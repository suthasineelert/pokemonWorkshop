package com.workshop.enumerator;

public enum FightStatus {
	STANDBY("standby"),
	FIGHT("fight"),
	RESULT("result");
	
	String value;
	FightStatus(String v){
		value = v;
	}
	
	public String getValue(){
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
