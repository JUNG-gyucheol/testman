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

@WebServlet("/boardDeleteProc")
public class boardDeleteProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		int result = 0;
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.oneBoard(num);
		
				
		if(id.equals(bean.getUserID())) {
			
			result = bdao.boardDelete(num);
			out.print(result);
	
		} else {
			out.println(0);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
