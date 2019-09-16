package com.sist;

import java.sql.SQLException;

public interface GscPanService {
	/**
	 * 전체 사용자 Level Upgrade
	 * @throws SQLException
	 */
	public void upgradeLevels() throws SQLException;
	
	/**
	 * 사용자 등록
	 * 최초등록시 Level은 Basic
	 * @param vo
	 */
	public void add(GscPanVO vo);
	
	public void upgradeLevelsLocalTx() throws Exception;
}
