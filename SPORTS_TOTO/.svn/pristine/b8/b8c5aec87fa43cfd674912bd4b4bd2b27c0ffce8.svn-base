package com.sist.dao;

import java.sql.*;
import java.util.*;

public class MainDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.111:1521:ORCL";
	private final String USER="scott";
	private final String PWD="tiger";
	
	public MainDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try {
			conn=DriverManager.getConnection(URL,"USER","PWD");
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void disConnection(){
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public List<MatchInfoVO> MatchAllData(){
		List<MatchInfoVO> list=new ArrayList<>();
		
		try {
			getConnection();
			String sql="SELECT leaguename,matchdate,starttime,toto_teamInfo.teamname "
					+ "FROM emp,dept,salgrade "
					+ "WHERE emp.deptno=dept.deptno "
					+ "AND sal BETWEEN losal AND hisal";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MatchInfoVO vo=new MatchInfoVO();
				
				list.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
}
/*

vo.setEmpno(rs.getInt(1));
vo.setName(rs.getString(2));
vo.setJob(rs.getString(3));
vo.setMgr(rs.getInt(4));
vo.setHiredate(rs.getDate(5));
vo.setSal(rs.getInt(6));
vo.setComm(rs.getInt(7));
vo.setDeptno(rs.getInt(8));

vo.getDvo().setDname(rs.getString(9));
vo.getDvo().setLoc(rs.getString(10));

vo.getSvo().setGrade(rs.getInt(11));
*/