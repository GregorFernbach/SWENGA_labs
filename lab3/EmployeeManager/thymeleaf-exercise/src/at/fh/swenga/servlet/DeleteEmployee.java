package at.fh.swenga.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.EmployeeManager;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/deleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssnString = request.getParameter("ssn");
		
		try {
			int ssn = Integer.parseInt(ssnString);
			
			HttpSession session = request.getSession(true);
			EmployeeManager employeeManager =(EmployeeManager)session.getAttribute("employeeManager");

			if (employeeManager!=null) {
				employeeManager.remove(ssn);
			}
			request.setAttribute("warningMessage", "Employee "+ssn+" deleted");
			RequestDispatcher rd = request.getRequestDispatcher("/listEmployees");
			rd.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
