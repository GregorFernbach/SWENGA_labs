package at.fh.swenga.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.EmployeeManager;
import at.fh.swenga.model.EmployeeModel;

/**
 * Servlet implementation class FillList
 */
@WebServlet("/fillEmployeeList")
public class FillEmployeeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession(true);
		
		EmployeeManager employeeManager = (EmployeeManager) session.getAttribute("employeeManager");

		if (employeeManager == null) {
			employeeManager = new EmployeeManager();
			session.setAttribute("employeeManager", employeeManager);
		}

		employeeManager.addDummyEmployees();
		

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listEmployees");
		rd.forward(request, response);
	}

}
