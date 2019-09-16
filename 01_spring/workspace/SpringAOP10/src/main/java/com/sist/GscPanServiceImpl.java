package com.sist;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;
/**
 * Upgrade Levels
 * @author sist1
 *
 */
public class GscPanServiceImpl implements GscPanService {
    Logger log = Logger.getLogger(this.getClass());
	public static final int MIN_SILVER_LOGIN_COUNT   = 50;//BASIC->SILVER 최소 로그인 횟수.
	public static final int MIN_GOLD_RECOMMAND_COUNT = 30;//SILVER->GOLD 최소 추천 횟수.
	
	private PlatformTransactionManager platformTransactionManager;
	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}


	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	private GscPanDao gscPanDao;
	public void setGscPanDao(GscPanDao gscPanDao) {
		this.gscPanDao = gscPanDao;
	}
	
	
	
	public void upgradeLevelsLocalTx() throws Exception{
		//-------------------------------------------------
		//Tx start
		//-------------------------------------------------
			TransactionSynchronizationManager.initSynchronization();
			Connection c = DataSourceUtils.getConnection(dataSource);
		//-------------------------------------------------
		//autocommit false
		//-------------------------------------------------
			c.setAutoCommit(false);
		try{
		

			List<GscPanVO> list=gscPanDao.getAll();
			
			for(GscPanVO vo:list){
				if(this.canUpgradeLevel(vo)){
					this.upgradeLevel(vo);
				}
			}			
		//-------------------------------------------------
		//Tx commit
		//-------------------------------------------------			
			c.commit();
		}catch(Exception e){
		//-------------------------------------------------
		//Tx rollback
		//-------------------------------------------------
			log.debug("=upgradeLevelsLocalTx Exception:==");
			log.debug(e.getMessage());
			log.debug("======================");
			c.rollback();
			throw e;
		}finally{
		//-------------------------------------------------
		//자원반납
		//-------------------------------------------------
			DataSourceUtils.releaseConnection(c, dataSource);
			TransactionSynchronizationManager.unbindResource(dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
	}	
	/**
	 * BASIC->SILVER
	 * SILVER->GOLD
	 * @param vo
	 */
	protected void upgradeLevel(GscPanVO vo){
	    
		vo.upgradeLevel();
		this.gscPanDao.update(vo);
	}
	/**
	 * Upgrade Level대상 인지 확인
	 * @param vo
	 * @return true/false
	 */
	private boolean canUpgradeLevel(GscPanVO vo){
		Level curLevel = vo.getLevel();
		switch(curLevel)
		{
			case BASIC:  return (vo.getLogin()>=MIN_SILVER_LOGIN_COUNT);
			case SILVER: return (vo.getRecommand()>=MIN_GOLD_RECOMMAND_COUNT);
			case GOLD:   return false;
			default:     throw new IllegalArgumentException("Unknown Level"+curLevel);
		}
	}
	
	
	/**
	 * 사용자 Level Up
	 * 1.1. 사용자 레벨은 BASIC,SILVER,GOLD
	 * 1.2. 사용자가 처음 가입 하면 BASIC, 이후 활동 상황에 따라 등업
	 * 1.3. 가입후 50회 로그인을 하면 BASIC에서 SILVER로 등업된다.
	 * 1.4. SILVER 레벨이면서 30번 이상 추천 받으면 GOLD가 된다.
	 * 1.5. 레벨변경 작업은 일정주기를 가지고 일괄 진행된다.
        전제사용자 조회
        for(){
         Boolean flag = false;
         if(BASIC && 로그인>=50){
            vo.setLevel(SILVER);
            flag = true;
         }else if(SILVER && 추천>=30){
            vo.setLevel(GOLD);
            flag = true;
         }else if(GOLD){
            flag = false;
         }else {
            flag = false;
         }
         
         if(
         
         flag == true)dao.update(vo);
        }
	 */
	public void upgradeLevels() throws SQLException {
		// TODO Auto-generated method stub
		List<GscPanVO> list=gscPanDao.getAll();
		
		for(GscPanVO vo:list){
			if(this.canUpgradeLevel(vo)){
				this.upgradeLevel(vo);
			}
		}
	}
	
	/**
	 * 1.Level이 Null이면 Level
	 *   을 Basic
	 */
	public void add(GscPanVO vo) {
		if(vo.getLevel() == null){
			vo.setLevel(Level.BASIC);
		}
		
		gscPanDao.add(vo);
	}

}
