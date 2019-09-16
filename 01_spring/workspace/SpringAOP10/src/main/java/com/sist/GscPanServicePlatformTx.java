package com.sist;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class GscPanServicePlatformTx implements GscPanService {
	Logger log = Logger.getLogger(this.getClass());
	private GscPanService gscPanService;
	public void setGscPanService(GscPanService gscPanService) {
		this.gscPanService = gscPanService;
	}

	private PlatformTransactionManager platformTransactionManager;
	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	
	public void upgradeLevels() throws SQLException {
//		PlatformTransactionManager platformTransactionManager=
//				new DataSourceTransactionManager(dataSource);
		
	    TransactionStatus status = 
	               platformTransactionManager.getTransaction(
			                  new DefaultTransactionDefinition());
		try{
			gscPanService.upgradeLevels();
			
			platformTransactionManager.commit(status);
		}catch(SQLException e){
			log.debug("GscPanServicePlatformTx:rollback"+e);
			platformTransactionManager.rollback(status);
			throw e;
		}

	}



	public void add(GscPanVO vo) {
		gscPanService.add(vo);
	}

	public void upgradeLevelsLocalTx() throws Exception {
		gscPanService.upgradeLevelsLocalTx();

	}

}
