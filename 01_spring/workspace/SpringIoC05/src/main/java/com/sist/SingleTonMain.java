package com.sist;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Bean의 싱글톤 여부 확인 
 * 객체1 GscPanDao
 * 객체2 GscPanDao
 * 객체1==객체2
 * @author sist1
 *
 */
public class SingleTonMain {
    static Logger log=Logger.getLogger(SingleTonMain.class);
	public static void main(String[] args) {
		//객체 메모리에 생성.
		ApplicationContext context = 
		 new AnnotationConfigApplicationContext(CountingDaoFactory.class);

		
		GscPanDao  dao01 = 
		(GscPanDao)context.getBean("gscPanDao",GscPanDao.class);
		
		GscPanDao  dao02 = 
				(GscPanDao)context.getBean("gscPanDao",GscPanDao.class);
		
		log.debug("dao01:"+dao01);
		log.debug("dao02:"+dao02);
		
//		GscPanDao  dao03 =new GscPanDao();
//		GscPanDao  dao04 =new GscPanDao();
//		log.debug("dao03:"+dao03);
//		log.debug("dao04:"+dao04);
		
		
		
		if(dao01 == dao02){
			log.debug("객체가 같다.");
		}else{
			log.debug("객체가 다르다.");
		}
		
	}

}






