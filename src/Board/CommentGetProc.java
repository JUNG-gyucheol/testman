package Board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CommentGetProc")
public class CommentGetProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		PrintWriter out = response.getWriter();
		out.println(getCom(num));
		
	}
	public String getCom(int num) {
		
		BoardDAO bdao = new BoardDAO();
		Vector<CommentBean> vec = bdao.getComment(num);
		StringBuffer str = new StringBuffer("");
		for(int i=0; i < vec.size(); i++) {
			str.append("<div class=\"parent\">"+vec.get(i).getUser_id()+"&nbsp;&nbsp;"+vec.get(i).getCom_date()+"<br><br>"
					+ "<div>"+vec.get(i).getComment() +"</div>"
					+"<div class=\"sub\"> <a>수정</a>&nbsp;&nbsp; <a>삭제</a></div></div>");
		}
		
		return str.toString();
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
