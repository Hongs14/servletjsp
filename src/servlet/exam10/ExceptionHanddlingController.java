package servlet.exam10;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="exam10.ExceptionHanddlingController", urlPatterns="/exam10/ExceptionHanddlingController")
public class ExceptionHanddlingController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			String data = "1oo";
			int num = Integer.parseInt(data);
			request.getRequestDispatcher("ContentController").forward(request, response);
		
		} catch(Exception e) {
			request.getRequestDispatcher("WEB-INF/views/exam10/error_500.jsp").forward(request, response);
		}
	}
}