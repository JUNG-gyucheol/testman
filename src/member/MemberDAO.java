package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet  rs;
	
	public void getCon(){
		
		try{
			Context initctx = new InitialContext();
			Context envctx = (Context)initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/mysql");
			conn = ds.getConnection();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void memeberJoin(MemberBean mbean){
		
		getCon();
		
		try {
			String sql = "INSERT INTO user_member values(?,?,?,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbean.getName());
			pstmt.setString(2, mbean.getId());
			pstmt.setString(3, mbean.getPassword());
			pstmt.setString(4, mbean.getPhone());
			pstmt.setInt(5, mbean.getTel());
			pstmt.setString(6, mbean.getEmail());
			pstmt.setString(7, mbean.getSex());
			pstmt.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int memberLogin(String id, String pass){
		
		getCon();
		
		try {
			String sql = "SELECT id , password FROM user_member WHERE id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(pass.equals(rs.getString(2)))
					return 2;
				else
					return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int idCheck(String id) {
		 
		getCon();
		
		try {
			String sql = "select id from user_member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 0;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
