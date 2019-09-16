package com.sist;
/**
 * BASIC,SILVER,GOLD
 * @author sist1
 *
 */
public enum Level {
	BASIC(1),SILVER(2),GOLD(3);
	
	private final int value;
	
	Level(int value){
		this.value = value;
	}
	
	/**
	 * DB처리용 int value
	 * @return int
	 */
	public int intValue(){
		return value;
	}
	
	/**
	 * 
	 * @param value
	 * @return Level enum
	 */
	public static Level valueOf(int value){
		switch(value){
		case 1: return BASIC;
		case 2: return SILVER;
		case 3: return GOLD;
		default: throw new AssertionError("Unknown value"+value);
		}
	}
	
}
