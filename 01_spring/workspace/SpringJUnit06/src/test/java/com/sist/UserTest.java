package com.sist;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//Spring 테스트 컨텍스트 지정
@ContextConfiguration(locations="/applicationContext.xml")//애플리케이션 컨텍스트 위치
public class UserTest {
	Logger log = Logger.getLogger(this.getClass());
	//스프링 테스트 컨텍스트에 의해 자동 객체 주입
	@Autowired
	private ApplicationContext context;
	
	/**
	 * @Test 수행되기전 먼저 수행.
	 */
	@Before
	public void setup(){
		log.debug("Before context:"+context);
	}
	
	/**
	 * @Test 수행되기전 먼저 수행.
	 */
	@After
	public void tearDown(){
		log.debug("=============");
		log.debug("@After");
		log.debug("=============");
	}
	
	
	@Test(timeout=1)
	public void addAndGet() throws SQLException{
		int expected =1;
		int actual =1;
		
//		for(int i=0;i<3000000;i++){
//			//log.debug("i"+i);
//		}
		
		log.debug("=============");
		log.debug("@addAndGet");
		log.debug("=============");
		
		assertEquals(expected, actual);
		log.debug("addAndGet");
	}
	
	@Test
	public void addAndGet01() throws SQLException{
		log.debug("=============");
		log.debug("@addAndGet01");
		log.debug("=============");		
	}	
	
	
	
}
