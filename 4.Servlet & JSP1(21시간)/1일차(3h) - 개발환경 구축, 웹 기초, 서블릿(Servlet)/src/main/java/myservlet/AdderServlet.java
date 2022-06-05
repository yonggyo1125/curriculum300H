package myservlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class AdderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String str1 = request.getParameter("num1");
		String str2 = request.getParameter("num2");
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		int sum = num1 + num2;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<head><title>µ¡¼À ÇÁ·Î±×·¥ - °á°úÈ­¸é</title></head>");
		out.println("<body>");
		out.printf("%d + %d = %d", num1, num2, sum);
		out.println("</body>");
		out.println("</html>");
		
	}
}
