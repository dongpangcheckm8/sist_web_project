package com.sist.dao;

import java.util.*;

import com.sist.conn.DBConOracle;
import com.sist.conn.SuperCon;
import com.sist.freeboard.BoardVO;

import java.sql.*;

public class MemberDAO {
	private PreparedStatement ps;
	private Connection conn = null;
	private SuperCon connMaker = null;
	private static MemberDAO dao;
	private static ChargeDAO Cdao;

	public MemberDAO(SuperCon spCon) {
		connMaker = spCon;
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public int isLogin(String id, String pwd) {
		int result = 0;

		try {
			conn = connMaker.getConnection();

			String sql = "SELECT COUNT(*) FROM toto_member " + "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			int count = rs.getInt(1);
			rs.close();

			if (count == 0) {
				result = 0;
			} else {
				sql = "SELECT pwd FROM toto_member " + "WHERE id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();

				rs.next();
				String db_pwd = rs.getString(1);
				rs.close();

				if (db_pwd.equals(pwd)) {
					result = 2;
				} else {
					result = 1;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}

		return result;
	}

	public String getLogData(String id) {
		String result = "";

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT name FROM toto_member " + "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			result = rs.getString(1);
			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}

		return result;
	}

	public String getPermission(String id) {
		String result = "";

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT permission_level FROM toto_member " + "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			result = rs.getString(1);
			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}

		return result;
	}

	public void memberInsert(MemberVO vo) {
		try {
			conn = connMaker.getConnection();

			String sql = "INSERT INTO toto_member (permission_level,name,id,pwd,gender,email,address,phone,birthday) "
					+ "VALUES (2,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getGender());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getAddress());
			ps.setString(7, vo.getPhone());
			ps.setString(8, vo.getBirthday().toString());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(vo.getName());
			System.out.println("memberInsert : " + e.getMessage());
		} finally {
			disConnection();
		}
	}

	// 회원정보
	public List<MemberVO> memberAllData(String id) {
		List<MemberVO> list = new ArrayList<>();

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT id,name,gender,birthday,address,phone,email,regdate,gamemoney,pwd "
					+ "FROM toto_member " + "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setBirthday(rs.getString(4));
				vo.setAddress(rs.getString(5));
				vo.setPhone(rs.getString(6));
				vo.setEmail(rs.getString(7));
				vo.setRegdate(rs.getDate(8));
				vo.setGamemoney(rs.getString(9));
				vo.setPwd(rs.getString(10));
				list.add(vo);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

		return list;
	}

	// 회원정보 수정
	public List<MemberVO> memberUpdate(String g, String b, String a, String p, String e, String id) {
		List<MemberVO> list = new ArrayList<>();

		try {
			conn = connMaker.getConnection();
			String sql = "UPDATE toto_member SET " + "gender=?,birthday=?,address=?,phone=?,email=? " + "WHERE id=?";
			ps = conn.prepareStatement(sql);
			MemberVO vo = new MemberVO();
			ps.setString(1, g);
			ps.setString(2, b);
			ps.setString(3, a);
			ps.setString(4, p);
			ps.setString(5, e);
			ps.setString(6, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

		return list;
	}

	public boolean check(String id) {
		boolean result =false;
		try {
			conn = connMaker.getConnection();
			
			String sql="SELECT id FROM toto_member where id=?";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("IDCheck : " + e.getMessage());
		} finally {
			disConnection();
		}
		return result;
	}
	
	public List<MemberVO> getAllID(){
		List<MemberVO> list=new ArrayList<>();
		
		try {
			conn=connMaker.getConnection();
			String sql="SELECT id FROM toto_member";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MemberVO vo=new MemberVO();
				vo.setId(rs.getString(1));
				list.add(vo);
			}
			rs.close();
		}catch (Exception e) {
			System.out.println("getAllID : " + e.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
	

}










