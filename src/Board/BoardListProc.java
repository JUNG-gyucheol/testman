package Board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BoardListProc")
public class BoardListProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if( id != null) {
		
				
			int pageSize = 10;
			
			String pageNum = request.getParameter("pageNum");
			
			if(pageNum == null) {
				pageNum = "1";
			}
			
			int currentPage = Integer.parseInt(pageNum);
			int number = 0;
			
			BoardDAO bdao = new BoardDAO();
			int count = bdao.count();
			
			
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			
			
			ArrayList<BoardBean> list = bdao.boardList(startRow, endRow);
			
			number = count - (currentPage -1) * pageSize;
			
			request.setAttribute("number", number);
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			
			RequestDispatcher dis = request.getRequestDispatcher("board.jsp");
			dis.forward(request, response);
			} else {
				out.println(0);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
