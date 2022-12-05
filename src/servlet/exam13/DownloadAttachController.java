package servlet.exam13;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;

@WebServlet(name="exam13.DownloadAttachController", urlPatterns="/exam13/DownloadAttachController")
public class DownloadAttachController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardService boardService= (BoardService) request.getServletContext().getAttribute("boardService");
		Board board = boardService.getBoard(bno);
		
		String fileName = board.getBfileName(); //시작행과 헤더에는 영어와 숫자뿐!!
		String filePath = "C:/Temp/download/"+board.getBsavedName(); //서버에 올릴 이름(유일해야 함)
		String contentType = board.getBfileType();
		
		//HTTP 응답에 Content-Type 헤더를 추가
		response.setContentType(contentType);
		
		//브라우저의 종류 얻기
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE일 경우
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			//Chrome, Edge, FireFox, safari일 경우
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			
		}
		System.out.println(fileName);
		
		//Http 응답에 Content-Disposition 헤더를 추가 
		response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName +"\"");
		
		ServletOutputStream sos = response.getOutputStream(); //이미지는 이걸 써야 함
		
		Path path = Paths.get(filePath);
		Files.copy(path, sos);
		sos.flush();
		sos.close();
		
	}
}