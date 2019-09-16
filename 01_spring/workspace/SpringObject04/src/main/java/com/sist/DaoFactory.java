package com.sist;

/**
 * Dao 객체생성
 * @author sist1
 *
 */
public class DaoFactory {
	/**
	 * SGscPanDao 객체 생성
	 * @return GscPanDao
	 */
	public GscPanDao gscPanDaoS(){
		ConnectiomMaker connection=new SGscPanDao();
		GscPanDao dao =new GscPanDao(connection);
		return dao;
	}
	
	/**
	 * KGscPanDao 객체 생성
	 * @return GscPanDao
	 */
	public GscPanDao gscPanDaoK(){
		ConnectiomMaker connection=new KGscPanDao();
		GscPanDao dao =new GscPanDao(connection);
		return dao;
	}
	
}
