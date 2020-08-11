package Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reWriteGetFormProc")
public class reWriteGetFormProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			int num = Integer.parseInt(request.getParameter("num"));
			
			BoardBean bean = new BoardDAO().oneBoard(num);
			
			int reg = bean.getReg();
			int reg_step = bean.getReg_step();
			int reg_count = bean.getReg_count();
			
			request.setAttribute("num", num);
			request.setAttribute("reg", reg);
			request.setAttribute("reg_step", reg_step);
			request.setAttribute("reg_count", reg_count);
			
			RequestDispatcher dis = request.getRequestDispatcher("reWriteForm.jsp");
			dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
