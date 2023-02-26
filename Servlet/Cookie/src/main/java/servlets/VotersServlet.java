package servlets;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Voters;

/**
 * Servlet implementation class VotersServlet
 */
@WebServlet("/voter")
public class VotersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// after redirection controller comes here
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pr = response.getWriter()) {
//********************************Read Cookie**************************************************//			

			// take cookie data comes via request here
			// getCookies method present inside HttpServletRequest
			Cookie[] c = request.getCookies();
			// check cookie present or not
			if (c != null) {
				// traverse through array of cookie
				for (Cookie cookie : c) {
					// check coming cookie data is matching iwth our created or not
					if (cookie.getName().equals("cln_details")) {
						// if matched print data from cookie
						pr.print("<h4>logged user :: " + cookie.getValue() + "</h4><a href='Logout'>Log Out</a>");
					} else {
						// if cookies name not matched give proper msg
						pr.print("<h4>logged user but cooke name not matched ::</h4>");
					}
				}
			} else { // if cookies are not accepted by client browser
				pr.print("cookies not accepted by user");

			}
		}

	}

}
