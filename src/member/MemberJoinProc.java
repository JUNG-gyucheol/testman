package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MemberJoinProc")
public class MemberJoinProc extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			MemberBean mbean = new MemberBean();
			
			mbean.setName(request.getParameter("name"));
			mbean.setId(request.getParameter("id"));
			mbean.setPassword(request.getParameter("pass1"));
			mbean.setPhone(request.getParameter("phone"));
			mbean.setTel(Integer.parseInt(request.getParameter("tel")));
			mbean.setEmail(request.getParameter("email"));
			mbean.setSex(request.getParameter("sex"));
			
			MemberDAO mdao = new MemberDAO();
			mdao.memeberJoin(mbean);
			
			RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
			dis.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
