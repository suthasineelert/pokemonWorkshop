package com.workshop.enumerator;

public enum AttackType {
	HAMMER("hammer"),
	SCISSOR("scissor"),
	PAPER("paper");
	
	String value;
	AttackType(String v){
		value = v;
	}
	
	public String getValue(){
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	public int checkResult(AttackType aType){
		switch(this){
		case HAMMER:
				if(aType == HAMMER) return 0;
				if(aType == SCISSOR) return 1;
				return -1;
		case SCISSOR:
			if(aType == HAMMER) return -1;
			if(aType == SCISSOR) return 0;
			return 1;
		default:
			if(aType == HAMMER) return 1;
			if(aType == SCISSOR) return -1;
			return 0;
		}
	} 
}
