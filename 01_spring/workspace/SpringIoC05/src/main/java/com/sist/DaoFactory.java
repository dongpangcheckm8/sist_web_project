package com.sist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dao 객체생성
 * @author sist1
 *
 */
//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 정보
@Configuration 
public class DaoFactory {
	/**
	 * SGscPanDao 객체 생성
	 * @return GscPanDao
	 */
	//오브젝트 생성 담당
	@Bean
	public GscPanDao gscPanDao(){
		//GscPanDao dao =new GscPanDao(connectiomMaker());
		return new GscPanDao(connectiomMaker());
	}
	
//	/**
//	 * KGscPanDao 객체 생성
//	 * @return GscPanDao
//	 */
//	public GscPanDao gscPanDaoK(){
//		ConnectiomMaker connection=new KGscPanDao();
//		GscPanDao dao =new GscPanDao(connection);
//		return dao;
//	}
    
	@Bean
	public ConnectiomMaker connectiomMaker(){
		return new SConnectionMaker();
	}
	 
}
