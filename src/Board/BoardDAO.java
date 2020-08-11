package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
		
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/mysql");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int count(){
		
		getCon();
		int count =0;
		
		try {
			String sql = "select count(*) from pro1_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<BoardBean> boardList(int start, int end){
		
		getCon();
		ArrayList<BoardBean> list = new ArrayList<>();
		
		try {
			String sql = "select * from(select A.* , @rownum:=@rownum+1 as rnum from(select * from pro1_board , (select @rownum:=0) c order by reg desc, reg_step asc,reg_count asc)  A) b "
							+"where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setSubject(rs.getString(2));
				bean.setUserID(rs.getString(3));
				bean.setDate(rs.getString(4));
				bean.setContent(rs.getString(5));
				bean.setReg(rs.getInt(6));
				bean.setReg_step(rs.getInt(7));
				bean.setReg_count(rs.getInt(8));
				bean.setCount(rs.getInt(9));
				
				list.add(bean);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}
	public void insertBoard(BoardBean bean) {
		getCon();
		int num = 0;
		int reg = 0;
		int reg_step = 1;
		int reg_count = 1;
		
		try {
			String regSql ="select MAX(board_num),MAX(reg) from pro1_board";
			pstmt = conn.prepareStatement(regSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1) + 1;
				reg = rs.getInt(2) + 1;
			}
			String sql = "INSERT INTO pro1_board VALUES(?,?,?,now(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getUserID());
			pstmt.setString(4, bean.getContent());
			pstmt.setInt(5, reg);
			pstmt.setInt(6, reg_step);
			pstmt.setInt(7, reg_count);
			pstmt.setInt(8, 0);
			pstmt.executeUpdate();
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public BoardBean oneBoard(int num) {
		
		getCon();
		BoardBean bean = new BoardBean();
		try {
			String conuntSql = "UPDATE pro1_board SET count = count + 1 WHERE board_num = ?";
			pstmt = conn.prepareStatement(conuntSql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		
			String sql = "SELECT * FROM pro1_board WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setSubject(rs.getString(2));
				bean.setUserID(rs.getString(3));
				bean.setDate(rs.getString(4));
				bean.setContent(rs.getString(5));
				bean.setReg(rs.getInt(6));
				bean.setReg_step(rs.getInt(7));
				bean.setReg_count(rs.getInt(8));
				bean.setCount(rs.getInt(9));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	public void insertReWrite(int num , String id , String subject, String content, int reg,int reg_step,int reg_count) {
		
		int boardNum = 0;
		getCon();
		try {
			String regCountSql = "UPDATE pro1_board SET reg_count = reg_count + 1 WHERE reg_count > 1 and reg = ? ";
			pstmt = conn.prepareStatement(regCountSql);
			pstmt.setInt(1, reg);
			pstmt.executeUpdate();
			
			String boardNumSql = "SELECT MAX(board_num) FROM pro1_board";
			pstmt = conn.prepareStatement(boardNumSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardNum = rs.getInt(1) + 1;
			}
			
			String sql = "INSERT INTO pro1_board VALUES(?,?,?,now(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, subject);
			pstmt.setString(3, id);
			pstmt.setString(4, content);
			pstmt.setInt(5, reg);
			pstmt.setInt(6, reg_step+1);
			pstmt.setInt(7, reg_count+1);
			pstmt.setInt(8, 0);
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void boardUpdate(int num, String subject, String content) {
		
		getCon();
		
		try {
			String sql ="UPDATE pro1_board SET board_subject = ?, board_content = ? WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			pstmt.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int boardDelete(int num) {
		
		getCon();
		int result = 0;
		try {
			String sql = "DELETE FROM pro1_board WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertCom(int num, String id, String comment) {
		
		getCon();
		int commNum = 0;
		int result = 0;
		try {
			String maxSql = "SELECT MAX(com_num)FROM comment";
			pstmt = conn.prepareStatement(maxSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				commNum = rs.getInt(1) + 1;
			}
			String sql = "INSERT INTO comment VALUES(?,?,?,?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNum);
			pstmt.setInt(2, num);
			pstmt.setString(3, id);
			pstmt.setString(4, comment);
			result = pstmt.executeUpdate();
			
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Vector<CommentBean> getComment(int num) {
		
		Vector<CommentBean> vec = new Vector<>();
		getCon();
		
		try {
			String sql = "SELECT * FROM comment WHERE board_num = ? ORDER BY com_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentBean cb = new CommentBean();
				
				cb.setCom_num(rs.getInt(1));
				cb.setBoard_num(rs.getInt(2));
				cb.setUser_id(rs.getString(3));
				cb.setComment(rs.getString(4));
				cb.setCom_date(rs.getString(5));
				
				vec.add(cb);
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vec;
	}

}
