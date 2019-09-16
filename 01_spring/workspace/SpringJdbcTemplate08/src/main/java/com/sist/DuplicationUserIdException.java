package com.sist;

import org.apache.log4j.Logger;

/**
 * Id중복 예외
 * @author sist1
 *
 */
public class DuplicationUserIdException extends RuntimeException {
    Logger log=Logger.getLogger(this.getClass());
	
	public DuplicationUserIdException(){}
	public DuplicationUserIdException(String cause){
		super(cause);
		log.debug("============================");
		log.debug(cause);
		log.debug("============================");		
	}
}
