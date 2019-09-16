package com.sist;

import java.sql.SQLException;
import java.util.List;

/**
 * 사용자관리 Dao interface
 * @author sist1
 *
 */
public interface GscPanDao {

	public int update(GscPanVO gscPanVO);
	/**
	 * GSC_PAN 전체 조회
	 * @return List<GscPanVO>
	 */
	public List<GscPanVO> getAll();
	/**
	 * 전체글 Count
	 * @return int
	 * @throws SQLException
	 */
	public int getCount()throws SQLException;
	
	/**
	 * 전체삭제
	 * @return int
	 */
	public int deleteAll();
	
	/**
	 * 위원회 멤버 추가
	 * @param pan
	 */
	public void add(final GscPanVO pan)
		throws DuplicationUserIdException;	
	
	/**
     * 위원회 멤버 단건 조회
     * @param cmId
     * @return GscPanVO 
     */     
	public GscPanVO get(String cmId) throws UnknownUserException;
	
	
}
