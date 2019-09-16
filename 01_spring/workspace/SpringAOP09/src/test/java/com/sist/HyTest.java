package com.sist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//Spring 테스트 컨텍스트 지정
@ContextConfiguration(locations="/applicationContext.xml")//애플리케이션 컨텍스트 위치
public class HyTest {
	Logger log = Logger.getLogger(HyTest.class);
	//스프링 테스트 컨텍스트에 의해 자동 객체 주입
	@Autowired
	private ApplicationContext context;	
	private GscPanDao  dao;
	private GscPanVO pan01;
	private GscPanVO pan02;
	private GscPanVO pan03;
	
	
	@Before
	public void setup(){
		pan01=new GscPanVO("james01","1호","1234","0",Level.BASIC,1,0);
		pan02=new GscPanVO("james02","2호","1235","0",Level.SILVER,55,10);
		pan03=new GscPanVO("james03","3호","1236","0",Level.GOLD,100,40);
		
		
		
		log.debug("Before HY:"+context);
		dao = 
		(GscPanDao)context.getBean("gscPanDao",GscPanDao.class);
	}
	
	
	/**
	 * 0.Data건 2건 입력
	 * 1.수정 Data 1건 필요.
	 * 2.1건  update
	 * 3.비교 수정건과 
	 * 4.비교 수정되지 않은건과 비교.
	 */
	@Test
	public void update(){
		//---------------------------------------//
		//전체삭제                                                                                         //
		//---------------------------------------//			
		int deleteFlag = dao.deleteAll();
		
		dao.add(pan01);
		dao.add(pan02);
		
		pan02.setName("이상무");
		pan02.setPassword("12349");
		pan02.setUseYn("1");
		//Level.BASIC,1,0);
		pan02.setLevel(Level.GOLD);
		pan02.setLogin(990);
		pan02.setRecommand(90);	
		
		int flag = dao.update(pan02);
		log.debug("=flag(update)="+flag);
		
		GscPanVO oPan01=dao.get(pan01.getCmId());
		GscPanVO oPan02=dao.get(pan02.getCmId());
		
		assertThat(pan01.equals(oPan02),is(false));
		
		assertThat(pan02.equals(oPan02),is(true));
		
	}
	
	
	
	
	@Test
	public void addAndGet01() throws SQLException{
		log.debug("=============");
		log.debug("@addAndGet01");
		log.debug("=============");		
	}
	
	//@Test
	public void addAndGet() throws SQLException{
				
		GscPanVO pan=new GscPanVO();
		pan.setCmId("james");
		pan.setName("다현아빠");
		pan.setPassword("1234");
		pan.setUseYn("0");
		//Level.BASIC,1,0);
		pan.setLevel(Level.BASIC);
		pan.setLogin(1);
		pan.setRecommand(0);

		//---------------------------------------//
		//전체삭제                                                                                         //
		//---------------------------------------//			
		int deleteFlag = dao.deleteAll();
		assertThat(dao.getCount(), is(dao.getAll().size()));
		
		log.debug("deleteFlag:"+deleteFlag);
		
		
		//---------------------------------------//
		//단건추가                                                                                          //
		//---------------------------------------//			
		dao.add(pan);
		assertThat(dao.getCount(), is(dao.getAll().size()));
		
		
		dao.add(pan01);
		assertThat(dao.getCount(), is(dao.getAll().size()));
		
		dao.add(pan02);
		assertThat(dao.getCount(), is(dao.getAll().size()));
		
		dao.add(pan03);
		assertThat(dao.getCount(), is(dao.getAll().size()));		
		//---------------------------------------//
		//1건 입력==1건 조회                                                                      //
		//---------------------------------------//			
		GscPanVO pan2=dao.get(pan.getCmId());
		GscPanVO oPan01=dao.get(pan01.getCmId());
		GscPanVO oPan02=dao.get(pan02.getCmId());
		GscPanVO oPan03=dao.get(pan03.getCmId());
		
		assertThat(pan.equals(pan2),is(true));
		assertThat(pan01.equals(oPan01),is(true));
		assertThat(pan02.equals(oPan02),is(true));
		assertThat(pan03.equals(oPan03),is(true));
		
		//assertThat(pan.equals(oPan01),is(true));
		
//			assertThat(pan.getCmId(),is(pan2.getCmId()));
//			assertThat(pan.getName(),is(pan2.getName()));
//			assertThat(pan.getPassword(),is(pan2.getPassword()));
//			assertThat(pan.getUseYn(),is(pan2.getUseYn()));
		
	}

	//ID중복이 발생하면 참
	//@Test(expected=DuplicationUserIdException.class)
	public  void add()throws DuplicationUserIdException{
		//---------------------------------------//
		//전체삭제                                                                                         //
		//---------------------------------------//			
		int deleteFlag = dao.deleteAll();		
		
		//---------------------------------------//
		//동일 데어터 추가                                                                              //
		//---------------------------------------//			
		dao.add(pan01);
		dao.add(pan01);
	}
	//UnknownUserException
	//@Test(expected=UnknownUserException.class)
	public  void getUnknownUser(){
		//---------------------------------------//
		//전체삭제                                                                                         //
		//---------------------------------------//			
		int deleteFlag = dao.deleteAll();
		//---------------------------------------//
		//동일 데어터 추가                                                                              //
		//---------------------------------------//			
		dao.add(pan01);
		dao.get("unknown_id");
	}
}
















