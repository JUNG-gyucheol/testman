package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberLoginProc")
public class MemberLoginProc extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.memberLogin(id, pass);
		if(result == 2){
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
			dis.forward(request, response);
		} else if(result == 1){
			out.println("<script>");
			out.println("alert('비밀번호를 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if(result == 0){
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if(result == -1){
			out.println("<script>");
			out.println("alert('오류입니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
