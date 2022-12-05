package servlet.exam13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;

@WebServlet(name="exam13.DetailViewController", urlPatterns="/exam13/DetailViewController")
public class DetailViewController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardService boardService= (BoardService) request.getServletContext().getAttribute("boardService");
		Board board = boardService.getBoard(bno);
		
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/views/exam13/detailView.jsp").forward(request, response);
		
	}
}