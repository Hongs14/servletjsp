package service;

import java.sql.Connection;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import dao.BoardDao;
import dto.Board;
import util.Pager;

public class BoardService {
	private ServletContext application;
	private DataSource ds;
	private BoardDao boardDao;

	public BoardService(ServletContext application) {
		this.application = application;
		boardDao = (BoardDao) application.getAttribute("boardDao"); 
		
		//server따로 설정 안해도 아래와 같은 방법으로 사용할 수 있음.
		ds = (DataSource) application.getAttribute("dataSource");
		
		//service가 만들어지기 전에  dao가 먼저 application에 저장되어져야 한다.
		//WebappContent에서 Dao를 먼저 하도록 순서 바꿔야 한다.
		/*try {
//			InitialContext ic = new InitialContext();
//			ds = (DataSource) ic.lookup("java:comp/env/jdbc/java");
		
			Connection conn = ds.getConnection();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public void write(Board board) {
		System.out.println("게시물을 저장합니다.");
		boardDao.insert(board, null);
	}

	public void write2(Board board) {
		BoardDao boardDao = (BoardDao) application.getAttribute("boardDao");
		Connection conn = null;
		try {
			conn = ds.getConnection();
			boardDao.insert2(board, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) {}
		}
	}

	public int getTotalBoardNum() {
		int result=0;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			result = boardDao.countRows(conn);
//			boardDao.countRows(board, conn);
			//조건에 따른 검색이라면 board자리에 조건이 와야함.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close();} catch (Exception e) {}
		}
		return result;
	}

	public List<Board> getPageList(Pager pager) {
		List<Board> result = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			result = boardDao.selectPageList(pager,conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close();} catch (Exception e) {}
		}
		return result;
	}

	public Board getBoard(int bno) {
		Board result = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			result = boardDao.selectBoard(bno,conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close();} catch (Exception e) {}
		}
		return result;
	}
}
