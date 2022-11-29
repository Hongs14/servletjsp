package servlet.exam04;

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

@WebServlet(name="exam04.FileResponseController", urlPatterns="/exam04/FileResponseController")
public class FileResponseController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//DB에서 가져와야 함
		String fileName = "사진1.jpg"; //시작행과 헤더에는 영어와 숫자뿐!!
		String filePath = "C:/Temp/photo1.jpg"; //서버에 올릴 이름(유일해야 함)
		String contentType = "image/jpeg";
		
		//HTTP 응답에 Content-Type 헤더를 추가
		response.setContentType(contentType);
//		response.setHeader("Cotent-Type", contentType); //위와 동일
		
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
		
		//Http 응답 본문에 파일데이터 출력하기
		ServletOutputStream sos = response.getOutputStream(); //이미지는 이걸 써야 함
		
		//방법1
		Path path = Paths.get(filePath);
		Files.copy(path, sos);
		sos.flush();
		sos.close();
		
		/*방법2 - 직접 객체 생성해서 하는 방식
		FileInputStream fis = new FileInputStream(filePath);
		
		
		byte[] data = new byte[1024];
		while(true) {
			int readByteNum = fis.read(data);
			if(readByteNum == -1) break;
			sos.write(data, 0, readByteNum);
		}
		sos.flush();
		fis.close();
		sos.close(); */
	}
}