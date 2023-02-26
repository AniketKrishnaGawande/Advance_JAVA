package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/Logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
// after clicking on log out controller will come here
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
// ********************************delete Cookie**************************************************//
		try (PrintWriter pr = response.getWriter()) {
			Cookie[] c = request.getCookies();
			if (c != null) {
				for (Cookie cookie : c) {
					if (cookie.getName().equals("cln_details")) {
						// setting age of cookie to 0 means delete cookie
						cookie.setMaxAge(0);
						// send cookie header to client
						response.addCookie(cookie);
						// redirect to Login page
						response.sendRedirect("Login.html");

					}
				}

			} else {

				pr.write("<h1>Cookie not accepted</h1>");
			}
		}
	}

}
