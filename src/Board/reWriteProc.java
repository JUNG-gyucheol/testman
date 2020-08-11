package Board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/reWriteProc")
public class reWriteProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int num = Integer.parseInt(request.getParameter("num"));
		int reg = Integer.parseInt(request.getParameter("reg"));
		int reg_step = Integer.parseInt(request.getParameter("reg_step"));
		int reg_count = Integer.parseInt(request.getParameter("reg_count"));
		
		BoardDAO bdao = new BoardDAO();
		bdao.insertReWrite(num, id, "[답변]"+subject, content, reg, reg_step, reg_count);
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListProc");
		dis.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request , response);
	}

}
