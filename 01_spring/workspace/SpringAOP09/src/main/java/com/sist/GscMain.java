package com.sist;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 위원회 검증
 * @author sist1
 * @version : 0.1
 * @since: 2017/11/14
 *
 */
public class GscMain {
	static Logger log = Logger.getLogger(GscMain.class);
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		//S사
		//GscPanDao dao =new SGscPanDao();
		//-----------------------------------------
		//GscPanDao가 사용할 구현 클래스를 결정하고 Object생성
		//-----------------------------------------
		//ConnectiomMaker connection=new SGscPanDao();
		
		//GscPanDao dao =new DaoFactory().gscPanDao();
//		ApplicationContext context = 
//				 new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = 
		 new GenericXmlApplicationContext("applicationContext.xml");
//		ApplicationContext context = 
//				 new AnnotationConfigApplicationContext(CountingDaoFactory.class);

				
		GscPanDao  dao = 
		(GscPanDao)context.getBean("gscPanDao",GscPanDao.class);
		

				
		GscPanVO pan=new GscPanVO();
		pan.setCmId("james");
		pan.setName("다현아빠");
		pan.setPassword("1234");
		pan.setUseYn("0");
		

			//---------------------------------------//
			//전체삭제                                                                                         //
			//---------------------------------------//			
			int deleteFlag = dao.deleteAll();
			log.debug("deleteFlag:"+deleteFlag);
			
			
			//---------------------------------------//
			//단건추가                                                                                          //
			//---------------------------------------//			
			dao.add(pan);
		
			
			//---------------------------------------//
			//1건 입력==1건 조회                                                                      //
			//---------------------------------------//			
			GscPanVO pan2=dao.get(pan.getCmId());
			if(!pan.getCmId().equals(pan2.getCmId())){
				log.debug("데이터 등록 실패:CmId");
			}else if(!pan.getName().equals(pan2.getName())){
				log.debug("데이터 등록 실패:getName");
			}else if(!pan.getPassword().equals(pan2.getPassword())){
				log.debug("데이터 등록 실패:getPassword");
			}else if(!pan.getUseYn().equals(pan2.getUseYn())){
				log.debug("데이터 등록 실패:getUseYn");
			}else{
				log.debug("데이터 등록 성공"+pan2.toString());
			}
	
		
		
	}

}
