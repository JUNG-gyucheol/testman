package Board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CommentProc")
public class CommentProc extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		 HttpSession session = request.getSession();
		 
		 int num = Integer.parseInt(request.getParameter("num"));
		 String id  = (String) session.getAttribute("id");
		 String comment = request.getParameter("comment");
		 
		 BoardDAO bdao = new BoardDAO();
		 int result = bdao.insertCom(num, id, comment);
		 PrintWriter out = response.getWriter();
		 out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
