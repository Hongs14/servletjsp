package servlet.exam05;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name="exam05.FileUploadController", urlPatterns="/exam05/FileUploadController")
@MultipartConfig(maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
public class FileUploadController extends HttpServlet {
	//클라이언트가 요청할때 마다 실행
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//한글 복원을 위해서 문자셋을 지정
		request.setCharacterEncoding("UTF-8");
		
		//멀티파트에선 이런 방식(request.getParameter("title");)으로 데이터를 넘길 수 없다
		//문자 파트의 정보 얻기
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");		
		System.out.println("title: "+ title);
		System.out.println("desc: "+ desc);
		
		//한 개의 파일 파트의 정보 얻기
		/*Part attachPart = request.getPart("attach");
		//파일이 실제로 전송되었는지 확인
		if(!attachPart.getSubmittedFileName().equals("") && attachPart.getSize() != 0) {		
			String filename = attachPart.getSubmittedFileName();
			long fileSize = attachPart.getSize();
			String contentType = attachPart.getContentType();
			System.out.println("fileName: "+ filename);
			System.out.println("fileSize: "+fileSize);
			System.out.println("contentType: "+contentType);
		}
		*/
		
		//두 개 이상의 파일 파트의 정보 얻기
		Collection<Part> parts = request.getParts();
		for(Part part : parts) {
			//파일 파트인지 확인 그리고 	실제로 전송되었는지 확인
			if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
				String fileName = part.getSubmittedFileName();
				long fileSize = part.getSize();
				String contentType = part.getContentType();
				System.out.println("fileName: "+ fileName);
				System.out.println("fileSize: "+fileSize);
				System.out.println("contentType: "+contentType);
				System.out.println();
				
				//파일을 파일 시스템에 저장
				String savedName = new Date().getTime()+"-"+fileName;
				String filePath = "C:/Temp/download/"+savedName;
				part.write(filePath); //파일 경로 넣기
				
			}
		}
		
		response.sendRedirect("RequestParamInfoController");
	}
	
}