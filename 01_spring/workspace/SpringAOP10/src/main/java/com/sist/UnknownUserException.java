package com.sist;

import org.apache.log4j.Logger;

/**
 * Id없는 경우 예외
 * @author sist1
 *
 */
public class UnknownUserException extends RuntimeException {
    Logger log=Logger.getLogger(this.getClass());
	
	public UnknownUserException(){}
	public UnknownUserException(String cause){
		super(cause);
		log.debug("============================");
		log.debug(cause);
		log.debug("============================");		
	}
}
