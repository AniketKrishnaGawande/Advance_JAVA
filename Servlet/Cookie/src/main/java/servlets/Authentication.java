package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.DBConnector;
import pojo.Voters;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/Login")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
// data member of own servlet class type for getting connected with DB 
	private DBConnector con;

	@Override
	public void init() throws ServletException {
		try {
			// constructor will be called inside which connection with mysql did
			con = new DBConnector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// post request comming from login form after clicking on submit button to
	// validate login credentials
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pr = response.getWriter()) {
			// to give response setting headers
			response.setContentType("text/html");
			// given details passed to dao layer method and checking if matched user exists
			// then return user else return null
			Voters user = con.Authenticate(request.getParameter("uname"), request.getParameter("psw"));
			if (user == null) {
				// if user not found give proper msg of browser
				pr.print("user not found");

			} else {

				// *********************COOKIE Attached********************************//
				// 1. create cookie instance
				Cookie c1 = new Cookie("cln_details", user.toString());

				// 2. add cookie header to send. method present in HttpServletResponse i/f
				response.addCookie(c1);

				// if user found then check voting status
				if (user.isStatus() == "voted")
					// if user already voted give that perticular msg on browser
					pr.print("user already voted");
				else
					// for successful login and not voted direct user to voting form
					// response.sendRedirect("votingForm.html");
					response.sendRedirect("voter"); // voting will be url pattern matched with
				// servlet class
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
