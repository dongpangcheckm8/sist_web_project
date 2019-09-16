package com.sist;

import static com.sist.GscPanServiceImpl.MIN_SILVER_LOGIN_COUNT;
import static com.sist.GscPanServiceImpl.MIN_GOLD_RECOMMAND_COUNT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)//Spring 테스트 컨텍스트 지정
@ContextConfiguration(locations="/test_applicationContext.xml")//애플리케이션 컨텍스트 위치
public class GscPanServiceTest {
	Logger log = Logger.getLogger(GscPanServiceTest.class);
	//스프링 테스트 컨텍스트에 의해 자동 객체 주입
	@Autowired
	private GscPanService gscPanService;	
	
	@Autowired
	private GscPanDao gscPanDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	List<GscPanVO> list;
	
	@Before
	public void setup(){
		log.debug("Before HY:"+gscPanService);
		log.debug("Before HY:"+gscPanDao);
		list = Arrays.asList(
				new GscPanVO("james01",	"1호",	"1234","0",	Level.BASIC	,MIN_SILVER_LOGIN_COUNT-1	,0 ),				
				new GscPanVO("james02",	"2호",	"1235","0",	Level.BASIC	,MIN_SILVER_LOGIN_COUNT	,0 ),
				new GscPanVO("james03",	"3호",	"1236","0",	Level.SILVER,60	,MIN_GOLD_RECOMMAND_COUNT-1),
				new GscPanVO("james04",	"4호",	"1236","0",	Level.SILVER,60	,MIN_GOLD_RECOMMAND_COUNT),				
				new GscPanVO("james05",	"5호",	"1237","0",	Level.GOLD	,70	,MIN_GOLD_RECOMMAND_COUNT+2)				
		);
		log.debug("Before list:"+list);
	}
	
	/**
	 * 사용자 생성시 레벨을 Basic으로 설정
	 * 1.데어터 clear : deleteAll()
	 * 2.Level없는 데이터 생성 : Basic
	 * 2.1. Level이 있는 데이터 생성: 레벨유지
	 * 3.사용자 추가
	 * 4.검증
	 */
	@Test
	@Ignore
	public void add(){
		//-----------------------------------
		//1.데어터 clear
		//-----------------------------------
		int deleteFlag = this.gscPanDao.deleteAll();
		log.debug("deleteFlag:"+deleteFlag);
		
		//-----------------------------------
		//2.Level없는 데이터 생성 
		//-----------------------------------		
		list.get(0).setLevel(null);
		
		//-----------------------------------
		//3.사용자 추가
		//-----------------------------------	
		for(GscPanVO vo:list){
			this.gscPanService.add(vo);
		}
		
		List<GscPanVO> afterAdd = gscPanDao.getAll();
		log.debug(afterAdd);
		//-----------------------------------
		//4.검증
		//-----------------------------------		
		this.checkLevel(afterAdd.get(0), Level.BASIC);
		this.checkLevel(afterAdd.get(1), Level.BASIC);
		this.checkLevel(afterAdd.get(2), Level.SILVER);
		this.checkLevel(afterAdd.get(3), Level.SILVER);
		this.checkLevel(afterAdd.get(4), Level.GOLD);
		
	}
	
	/**
	 * Bean Creation확인
	 */
	@Test
	public void createBean(){
		assertThat(this.gscPanService,is(notNullValue()));
		assertThat(this.gscPanDao,is(notNullValue()));
	}
	
	/**
	 * 1.전체삭제
	 * 2.5건 추가
	 * 2.1. 5건 추가 검증
	 * 3.레벨업
	 * 4.검증
	 * @throws Exception 
	 */
	@Test
	@Ignore
	public void upgradeLevelsLocalTx() throws Exception{
		//-----------------------------------------
		//1.전체 삭제
		//-----------------------------------------
		int deleteFlag = gscPanDao.deleteAll();
		log.debug("deleteFlag:"+deleteFlag);
		
		//-----------------------------------------
		//2.5건 추가
		//-----------------------------------------
		for(GscPanVO vo:list){
			gscPanDao.add(vo);
		}
		log.debug("추가건수:"+gscPanDao.getCount());
		assertThat(gscPanDao.getAll().size(), is(5));
		
		
		//-----------------------------------------
		//3.레벨업
		//-----------------------------------------
		//gscPanService.upgradeLevels();
		gscPanService.upgradeLevelsLocalTx();
		
		//-----------------------------------------
		//4.검증
		//-----------------------------------------		
		List<GscPanVO>  afterUpList = gscPanDao.getAll();
		checkLevel(afterUpList.get(0),Level.BASIC);
		checkLevel(afterUpList.get(1),Level.BASIC);
		checkLevel(afterUpList.get(2),Level.SILVER);
		checkLevel(afterUpList.get(3),Level.SILVER);
		checkLevel(afterUpList.get(4),Level.GOLD);
    }
	
	public void jdbcTran(){
//		Connection conn = null; 
//		PreparedStatement pstmt = null;
//		try{
//			conn.setAutoCommit(false);      
//			
//			pstmt.executeUpdate("update .... "); 
//			pstmt.executeUpdate("insert ....");
//			pstmt.executeUpdate("delete ... ");
//
//			conn.commit();       
//			...
//		}catch(SQLException sqle){
//
//		  if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}            
//		// Exception 발생시 rollback 한다.
//
//		}finally{
//			
//			conn.setAutoCommit(true);	
//		}
		   		
	}
	
	/**
	 * 1.전체삭제
	 * 2.5건 추가
	 * 2.1. 5건 추가 검증
	 * 3.레벨업
	 * 4.검증
	 * @throws Exception 
	 */
	@Test
	@Ignore
	public void upgradeLevels() throws Exception{
		//-----------------------------------------
		//1.전체 삭제
		//-----------------------------------------
		int deleteFlag = gscPanDao.deleteAll();
		log.debug("deleteFlag:"+deleteFlag);
		
		//-----------------------------------------
		//2.5건 추가
		//-----------------------------------------
		for(GscPanVO vo:list){
			gscPanDao.add(vo);
		}
		log.debug("추가건수:"+gscPanDao.getCount());
		assertThat(gscPanDao.getAll().size(), is(5));
		
		
		//-----------------------------------------
		//3.레벨업
		//-----------------------------------------
		gscPanService.upgradeLevels();
		
		//-----------------------------------------
		//4.검증
		//-----------------------------------------		
		List<GscPanVO>  afterUpList = gscPanDao.getAll();
		checkLevel(afterUpList.get(0),Level.BASIC);
		checkLevel(afterUpList.get(1),Level.SILVER);
		checkLevel(afterUpList.get(2),Level.SILVER);
		checkLevel(afterUpList.get(3),Level.GOLD);
		checkLevel(afterUpList.get(4),Level.GOLD);
    }
	
	//Level비교
	private void checkLevel(GscPanVO vo, Level expectLevel){
		assertThat(vo.getLevel(),is(expectLevel));
	}
	
	@Test(expected=TestUserServiceException.class)
	public void allOrNothingTx()throws Exception{
		GscPanServiceImpl  gscPanServiceImpl = 
				new TestUserService(list.get(3).getCmId());
		//수동 DI
		gscPanServiceImpl.setGscPanDao(this.gscPanDao);
		gscPanServiceImpl.setDataSource(dataSource);
		
		
		GscPanServicePlatformTx gscPanServicePlatformTx=new
				GscPanServicePlatformTx();
		gscPanServicePlatformTx.setPlatformTransactionManager(platformTransactionManager);
		gscPanServicePlatformTx.setGscPanService(gscPanServiceImpl);
		//-----------------------------------------
		//1.전체 삭제
		//-----------------------------------------
		int deleteFlag = gscPanDao.deleteAll();
		log.debug("deleteFlag:"+deleteFlag);
		
		//-----------------------------------------
		//2.5건 추가
		//-----------------------------------------
		for(GscPanVO vo:list){
			gscPanDao.add(vo);
		}
		log.debug("추가건수:"+gscPanDao.getCount());
		assertThat(gscPanDao.getAll().size(), is(5));
		
		try{
			
			//gscPanServiceImpl.upgradeLevels();
			//gscPanServiceImpl.upgradeLevelsLocalTx();
			gscPanServicePlatformTx.upgradeLevels();
			
		}catch(TestUserServiceException te){
			throw te;
		}
		
		checkLevel(list.get(1),Level.BASIC);
	}
	

	@Test(expected=TestUserServiceException.class)
	@Ignore
	public void allOrNothing()throws Exception{
		GscPanServiceImpl  gscPanServiceImpl = 
				new TestUserService(list.get(3).getCmId());
		//수동 DI
		gscPanServiceImpl.setGscPanDao(this.gscPanDao);
		gscPanServiceImpl.setDataSource(dataSource);
		
		//-----------------------------------------
		//1.전체 삭제
		//-----------------------------------------
		int deleteFlag = gscPanDao.deleteAll();
		log.debug("deleteFlag:"+deleteFlag);
		
		//-----------------------------------------
		//2.5건 추가
		//-----------------------------------------
		for(GscPanVO vo:list){
			gscPanDao.add(vo);
		}
		log.debug("추가건수:"+gscPanDao.getCount());
		assertThat(gscPanDao.getAll().size(), is(5));
		
		try{
			
			//gscPanServiceImpl.upgradeLevels();
			gscPanServiceImpl.upgradeLevelsLocalTx();
			
			
		}catch(TestUserServiceException te){
			throw te;
		}
		
		checkLevel(list.get(1),Level.BASIC);
	}
	
}
