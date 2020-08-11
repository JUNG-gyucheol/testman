package Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			int num = Integer.parseInt(request.getParameter("num"));
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			BoardDAO bdao = new BoardDAO();
			bdao.boardUpdate(num, subject, content);
			
			RequestDispatcher dis = request.getRequestDispatcher("BoardListProc");
			dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
