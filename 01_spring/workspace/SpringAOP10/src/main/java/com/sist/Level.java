package com.sist;
/**
 * BASIC,SILVER,GOLD
 * @author sist1
 *
 */
public enum Level {
	GOLD(3,null), SILVER(2,GOLD),BASIC(1,SILVER);
	
	private final int value;
	private final Level next;
	
	Level(int value, Level next){
		this.value = value;
		this.next = next;
	}
	
	
	public Level nextLevel(){
		return this.next;
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
