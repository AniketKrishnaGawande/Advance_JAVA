package postMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/postServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try (PrintWriter pr = response.getWriter()) {
			pr.print("data comes by post method<br>");
			pr.println("<h1>Name :: </h1>" + request.getParameter("name") + "<br>");
			pr.println("<h1>message :: </h1>" + request.getParameter("msg"));
			pr.print("color choosen :: " + Arrays.toString(request.getParameterValues("color")));
		// as color is check box it will come as array 
		}

	}
}