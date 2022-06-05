package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class BBSPostServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>게시판 글쓰기 - 결과화면</title></head>");
		out.println("<body>");
		out.printf("이름: %s <br>",name);
		out.printf("제목: %s <br>", title);
		out.println("-------------<br>");
		out.printf("<pre>%s</pre>", content);
		out.println("-------------<br>");
		out.println("저장되었습니다.");
		out.println("</body>");
		out.println("</html>");
	}
}
