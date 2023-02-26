package ownservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OwnServlet
 */

/*
 * url pattern is mapped with this class using annotation webServlet it means if
 * comming url is /AnnotationServlet then call methods present inside this class
 * based on http request type ie get post delete etc
 */
@WebServlet(value = "/AnnotationServlet" )
public class AnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
// if we have to open some resources like DB connection etc it is done in init otherwise do not
// override init method
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

		System.out.println("inside init");

	}

	// if for matching url pattern http request type is get then invoke this method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// at server side console below msg will gets printed
		System.out.println("inside do get");

		// informing client whihc kind of resp is comming
		response.setContentType("text/html");

		// to give resp we need printwriter instance comming from getWriter method
// try with resourse is used to close printwriter after completion
		try (PrintWriter pr = response.getWriter()) {

			// giving resp to client whihc will be printed on browser
			pr.print("invoked by servlet class");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

// this method is overrided when we have to cleanup resourses opened in init method like close DB connection etc
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("inside destroy");
	}

}
