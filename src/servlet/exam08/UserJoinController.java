package servlet.exam08;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import service.UserService;

@WebServlet(name="exam08.UserJoinController", urlPatterns="/exam08/UserJoinController")
public class UserJoinController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/views/exam08/joinForm.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//클라이언트가 보낸 데이터 얻기
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setUserName(request.getParameter("userName"));
		user.setUserPassword(request.getParameter("userPwd"));
//		user.setUserAge(Integer.parseInt(request.getParameter("userAge")));
//		user.setUserEmail(request.getParameter("userEmail"));
		
		//서비스로 회원 가입 요청//Service객체를 얻는 것 //주제 제일 중요
		ServletContext application = request.getServletContext();
		UserService userService = (UserService) application.getAttribute("userService");
		userService.join(user);
		response.sendRedirect("ContentController");
	}
}