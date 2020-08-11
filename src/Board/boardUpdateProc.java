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

@WebServlet("/boardUpdateProc")
public class boardUpdateProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		BoardBean bean = new BoardDAO().oneBoard(num);
		
		if(id.equals(bean.getUserID())) {
		
		request.setAttribute("bean", bean);
		
		RequestDispatcher dis = request.getRequestDispatcher("boardUpdateForm.jsp");
		dis.forward(request, response);
		} else {
			out.println("<script>");
			out.println("alert('수정할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
