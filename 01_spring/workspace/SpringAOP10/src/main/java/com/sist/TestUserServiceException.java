package com.sist;

public class TestUserServiceException extends RuntimeException {
	public TestUserServiceException(){
		super();
	}
	
	public TestUserServiceException(String msg){
		super(msg);
	}	
}
