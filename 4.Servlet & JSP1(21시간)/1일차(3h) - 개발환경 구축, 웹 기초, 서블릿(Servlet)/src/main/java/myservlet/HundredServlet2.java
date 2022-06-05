package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/hundred2")
public class HundredServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int total = 0;
		for(int i = 1; i < 101; i++)
			total += i;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.printf("1부터 100까지 합은 = %d", total);
		out.println("</body>");
		out.println("</html>");
	}
}
