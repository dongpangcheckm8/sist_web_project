package com.sist;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * 위원회 Dao
 * @author sist1
 * @version : 0.2
 * @since: 2017/11/14
 *         2017/11/14: getConnection 추상메소드로 변경
 *                     S/K사에 요청 사항 : DB를 유연하게 변경 하도록 추상클래스로 수정   
 *         2017/11/14: 다건추가 테스트 : getCount()추가
 *         2017/11/20: JdbcTemplte으로 변환         
 */
public class GscPanDao {
    Logger log = Logger.getLogger(GscPanDao.class);

    
    private RowMapper<GscPanVO> rowMapper 
    			= new RowMapper<GscPanVO>(){

		public GscPanVO mapRow(ResultSet rs, int rowNum) 
				throws SQLException {
			GscPanVO gscPanVO=new GscPanVO();
			gscPanVO.setCmId(rs.getString("CM_ID"));
			gscPanVO.setName(rs.getString("NAME"));
			gscPanVO.setPassword(rs.getString("PASSWORD"));
			gscPanVO.setUseYn(rs.getString("USE_YN"));
			//int -> Level
			gscPanVO.setLevel(Level.valueOf(rs.getInt("LEVELS")));
			gscPanVO.setLogin(rs.getInt("LOGIN"));
			gscPanVO.setRecommand(rs.getInt("RECOMMEND"));
			
			return gscPanVO;
		}

      };
    
    private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	public GscPanDao(){}
    
    
	public int update(GscPanVO gscPanVO){
		int flag = 0;
		StringBuilder sb=new StringBuilder();
		sb.append("  UPDATE GSC_PAN         \n");
		sb.append("         SET NAME   = ?, \n");
		sb.append("		     PASSWORD  = ?, \n");
		sb.append("			 USE_YN    = ?, \n");
		sb.append("			 LEVELS    = ?, \n");
		sb.append("			 LOGIN     = ?, \n");
		sb.append("			 RECOMMEND = ?  \n");
		sb.append("   WHERE CM_ID = ?       \n");		
		
        log.debug(sb.toString());
        log.debug("gscPanVO\n"+gscPanVO.toString());
        
		flag = this.jdbcTemplate.update(sb.toString()
				,gscPanVO.getName()
				,gscPanVO.getPassword()
				,gscPanVO.getUseYn()
				,gscPanVO.getLevel().intValue()
				,gscPanVO.getLogin()
				,gscPanVO.getRecommand()
				,gscPanVO.getCmId()
				);
		
		return flag;
	}
	
	
	/**
	 * GSC_PAN 전체 조회
	 * @return List<GscPanVO>
	 */
	public List<GscPanVO> getAll(){
		List<GscPanVO> list = null;
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT CM_ID,        \n");
        sb.append("       NAME,         \n");
        sb.append("       PASSWORD,     \n");
        sb.append("       USE_YN,       \n");
        sb.append("       LEVELS,       \n");
        sb.append("       LOGIN,        \n");
        sb.append("       RECOMMEND     \n");
        sb.append("  FROM GSC_PAN       \n");
        
        list = this.jdbcTemplate.query(sb.toString()
        		, rowMapper);
        
        log.debug(sb.toString());
        log.debug("getAll()\n"+list);
        
        return list;
	}
	
	
	/**
	 * 전체글 Count
	 * @return int
	 * @throws SQLException
	 */
	public int getCount()throws SQLException{
		int flag = 0;

        final StringBuilder sb=new StringBuilder();
        sb.append(" SELECT count(*) cnt  \n");
        sb.append("   FROM GSC_PAN       \n");
        
        flag = jdbcTemplate.query(
        		  new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						return con.prepareStatement(sb.toString());
					}
				}
        		, new ResultSetExtractor<Integer>() {
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						rs.next();
						return rs.getInt(1);
					}
				});
        log.debug("getCount()\n"+sb.toString()+"\tflag:"+flag);
        return flag;
	}
	

    
	/**
	 * 전체삭제
	 * @return int
	 */
	public int deleteAll(){
		int flag=0;		
		StringBuilder sb=new StringBuilder();
		sb.append("delete from gsc_pan ");	
		
		flag = this.jdbcTemplate.update(sb.toString());
		
		return flag;
	}
	
	/**
	 * 위원회 멤버 추가
	 * @param pan
	 */
	public void add(final GscPanVO pan)
		throws DuplicationUserIdException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO GSC_PAN \n");
		sb.append("  (                 \n");
		sb.append("    CM_ID,          \n");
		sb.append("    NAME,           \n");
		sb.append("    PASSWORD,       \n");
		sb.append("    USE_YN,         \n");
		sb.append("    LEVELS,         \n");
		sb.append("    LOGIN,          \n");
		sb.append("    RECOMMEND       \n");
		sb.append("  )                 \n");
		sb.append("  VALUES            \n");
		sb.append("  (                 \n");
		sb.append("    ?,              \n");
		sb.append("    ?,              \n");
		sb.append("    ?,              \n");
		sb.append("    ?,              \n");
		sb.append("    ?,              \n");
		sb.append("    ?,              \n");
		sb.append("    ?               \n");		
		sb.append("  )                 \n");
        
		log.debug("sb: "+sb.toString());
		log.debug("pan: "+pan);
        try{
	        this.jdbcTemplate.update(sb.toString()
	        		              , pan.getCmId()
	        		              , pan.getName()
	        		              , pan.getPassword()
	        		              , pan.getUseYn()
	        		              , pan.getLevel().intValue()
	        		              , pan.getLogin()
	        		              , pan.getRecommand()
	        		              
	        		);
        }catch(DataAccessException du){
        	log.debug("du: "+du.getMessage());
        	
        	throw new DuplicationUserIdException("아이디 중복!");
        }
	}
	
    /**
     * 위원회 멤버 단건 조회
     * @param cmId
     * @return GscPanVO 
     */     
	public GscPanVO get(String cmId) throws UnknownUserException {
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT CM_ID,        \n");
        sb.append("       NAME,         \n");
        sb.append("       PASSWORD,     \n");
        sb.append("       USE_YN,       \n");
        sb.append("       LEVELS,       \n");
        sb.append("       LOGIN,        \n");
        sb.append("       RECOMMEND     \n");
        sb.append("  FROM GSC_PAN       \n");
        sb.append(" WHERE CM_ID = ?     \n");
        try{
        GscPanVO pan = this.jdbcTemplate.queryForObject(sb.toString()
        		, new Object[]{cmId}
        		, rowMapper);
  
        log.debug("======================");
        log.debug("sql:\t"+sb.toString());
        log.debug("======================");
        
        log.debug("======================");
        log.debug("pan:\t"+cmId);
        log.debug("======================");  

        log.debug("======================");
        log.debug("return:\t"+pan);
        log.debug("======================");         
        return pan;
        }catch(DataAccessException d){
        	throw new UnknownUserException("ID가 없습니다."); 
        }        
	}	

	
}
