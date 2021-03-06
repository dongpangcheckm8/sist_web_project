package com.sist.freeboard;

import java.util.*;

import com.sist.conn.SuperCon;

import java.sql.*;

public class BoardDAO {
	private PreparedStatement ps;
	private Connection conn = null;
	private SuperCon connMaker = null;
	private static BoardDAO dao;
	
	public BoardDAO(SuperCon spCon){
		connMaker = spCon;
	}
	
	public void disConnection() {
		try {
			if(conn!=null)conn.close();
			if(ps!=null)ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public List<BoardVO> boardAllData(int page){
		List<BoardVO> list=new ArrayList<>();
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT postingno,subject,name,regdate,hit "
					+ "FROM toto_freeBoard "
					+ "ORDER BY regdate DESC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=0;
			int j=0;

			int pagecnt=(page*15)-15;
			while(rs.next()) {
				if(i<15 && j>=pagecnt) {
					BoardVO vo=new BoardVO();
					vo.setPostingNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					list.add(vo);
					i++;
				}
				j++;
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
	
	//총페이지
	public int boardTotalPage() {
		int total=0;
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT CEIL(count(*)/15) FROM toto_freeBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return total;
	}
	
	//내용보기
	public BoardVO boardContentData(int no) {
		
		BoardVO vo=new BoardVO();
		
		try {
			conn= connMaker.getConnection();
			String sql="UPDATE toto_freeBoard SET "
					+ "hit=hit+1 "
					+ "WHERE postingno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql="SELECT postingno,name,subject,content,regdate,hit "
					+ "FROM toto_freeBoard "
					+ "WHERE postingno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			vo.setPostingNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			rs.close();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return vo;
	}
	
	//수정 데이터 불러오기
	public BoardVO boardUpdateData(int no) {
		BoardVO vo=new BoardVO();
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT postingno,name,subject,content,regdate,hit "
					+ "FROM toto_freeBoard "
					+ "WHERE postingno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setPostingNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			rs.close();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return vo;
	}
	
	//수정
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT pwd FROM toto_freeBoard "
					+ "WHERE postingno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getPostingNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				//수정
				sql="UPDATE toto_freeBoard SET "
						+ "name=?,subject=?,content=? "
						+ "WHERE postingno=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getPostingNo());
				ps.executeUpdate();
			}else {
				bCheck=false;
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return bCheck;
	}
	
	//글쓰기
	public void boardInsert(BoardVO vo) {
		try {
			conn= connMaker.getConnection();
			String sql="INSERT INTO toto_freeBoard(postingno,name,subject,content,pwd) "
					+ "VALUES((SELECT NVL(MAX(postingno)+1,1) FROM toto_freeBoard),?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
	}
	
	//게시글 검색하기
	public List<BoardVO> boardFindData(String fs,String ss,int page){
		List<BoardVO> list=new ArrayList<>();
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT postingno,subject,name,regdate,hit "
					+ "FROM toto_freeBoard "
					+ "WHERE "+fs+" LIKE '%'||?||'%' "
					+ "ORDER BY regdate DESC";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs=ps.executeQuery();
			
			int i=0;
			int j=0;

			int pagecnt=(page*15)-15;
			while(rs.next()) {
				if(i<15 && j>=pagecnt) {
					BoardVO vo=new BoardVO();
					vo.setPostingNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					list.add(vo);
					i++;
				}
				j++;
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
	
	//검색 결과 총페이지
	public int boardFindTotalPage(String fs,String ss) {
		int total=0;
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT CEIL(count(*)/15) FROM toto_freeBoard "
					+ "WHERE "+fs+" LIKE '%'||?||'%' "
					+ "ORDER BY regdate DESC";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return total;
	}
	
	//삭제
	public void boardDelete(int no) {
		try {
			conn= connMaker.getConnection();
			String sql="DELETE FROM toto_freeBoard "
					+"WHERE postingno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
	}
	//메인화면 전용
		public List<BoardVO> freeboard_main() {
			List<BoardVO> list=new ArrayList<>();
			
			try {
				conn= connMaker.getConnection();
				String sql="SELECT postingno, name, subject, regdate FROM (SELECT postingno, name, subject, regdate FROM toto_freeboard ORDER BY regdate DESC) WHERE ROWNUM BETWEEN 1 AND 13";
				ps=conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					BoardVO vo=new BoardVO();
					vo.setPostingNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setSubject(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					list.add(vo);
				}
				rs.close();
			}catch (Exception e) {
				System.out.println("notice_main : " + e.getMessage());
			}finally {
				disConnection();
			}
			
			return list;
		}
		
		//텍스트 쓰기
		public static String replaceHtml(String srcString) { 
			if(srcString == null)
			return null;
			String rtnStr = srcString;
			
			try{
				rtnStr = rtnStr.replaceAll("&","&amp;");
				rtnStr = rtnStr.replaceAll("<","&lt;");
				rtnStr = rtnStr.replaceAll(">","&gt;");
				rtnStr = rtnStr.replaceAll("\"","&quot;");
				rtnStr = rtnStr.replaceAll(" ","&nbsp;");
				rtnStr = rtnStr.replaceAll("\u0020","&nbsp;");
				rtnStr = rtnStr.replaceAll("\n","<br/>");
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			System.out.println(rtnStr);
			
			return rtnStr;
		}
}
