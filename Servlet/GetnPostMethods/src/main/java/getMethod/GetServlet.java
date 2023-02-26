package getMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetServlet
 */
@WebServlet(value = "/getServlet", loadOnStartup = 1)  //loadOnStartup is 1 means eager
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// set header content
		response.setContentType("text/html");

		try (PrintWriter pr = response.getWriter()) {

			pr.print("data comes by get method<br>");
			// data will come in request
			pr.println("<h1>Name :: </h1>" + request.getParameter("name") + "<br>");
			// here parameter given to getPara method must be same as name property of
			// form's input tag
			pr.println("<h1>message :: </h1>" + request.getParameter("msg"));
			pr.print("color choosen :: "+Arrays.toString(request.getParameterValues("color")));
			// as color is check box it will come as array
		}

	}

}
