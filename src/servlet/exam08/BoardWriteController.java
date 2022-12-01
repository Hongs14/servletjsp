package servlet.exam08;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;

@WebServlet(name="exam08.BoardWriteController", urlPatterns="/exam08/BoardWriteController")
public class BoardWriteController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/views/exam08/writeForm.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//클라이언트가 보낸 데이터 얻기
		Board board = new Board();
		board.setBtitle(request.getParameter("btitle"));
		board.setBcontent(request.getParameter("bContent"));
		
		//서비스로 회원 가입 요청//Service객체를 얻는 것
		ServletContext application = request.getServletContext();
		BoardService boardService = (BoardService) application.getAttribute("board Service");
		boardService.write(board);
		
		response.sendRedirect("ContentController");
	}
}