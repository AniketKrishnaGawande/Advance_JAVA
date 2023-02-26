package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBConnector;
import pojo.Voters;

/**
 * Servlet implementation class CastVoting
 */
@WebServlet("/castVote")
public class CastVoting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnector con;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void init() throws ServletException {
		try {
			con = new DBConnector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter pr = response.getWriter()) {
			// get session object
			response.setContentType("text/html");
			// get session object here as user is old, existing session object present
			// inside heap will be returned
			HttpSession session = request.getSession();
             // get data from session. return type of getAtribute is object so type casted
			Voters voter = (Voters) session.getAttribute("voter_details");

			if (voter != null) {
				con.addVoting(request.getParameter("candidate"), voter.getUname());
				pr.print("<h2>you have voted successfully</h2><br><a href='Logout'>LogOut</a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
