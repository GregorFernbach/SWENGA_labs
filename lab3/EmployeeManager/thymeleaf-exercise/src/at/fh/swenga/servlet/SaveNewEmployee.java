package at.fh.swenga.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * Servlet implementation class SaveEmployee
 */
@WebServlet("/saveNewEmployee")
public class SaveNewEmployee extends HttpServlet {
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

		String ssnString = request.getParameter("ssn");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dayOfBirthString = request.getParameter("dayOfBirth");

		String errorMessage = "";
		
		//---- Convert SSN ----
		int ssn = 0;
		try {
			ssn = Integer.parseInt(ssnString);
		} catch (Exception e) {
			errorMessage += "SSN invalid";
		}

		// ---- Convert calendar -----
		Calendar dayOfBirth = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dayOfBirth.setTime(sdf.parse(dayOfBirthString));
		} catch (Exception e) {
			errorMessage += "Day of Birth invalid";
		}

		// Data Conversion ok? -> Change Employee
		if ("".equals(errorMessage)) {
			HttpSession session = request.getSession(true);
			EmployeeManager employeeManager = (EmployeeManager) session.getAttribute("employeeManager");
			EmployeeModel employee = employeeManager.getEmployeebySSN(ssn);

			if (employee != null) {
				errorMessage += "Employee already exists!";
			} else {
				EmployeeModel em = new EmployeeModel(ssn, firstName, lastName, dayOfBirth);
				employeeManager.addEmployee(em);
			}
		}
		if ("".equals(errorMessage)) {
			request.setAttribute("message", "New employee " + ssn + " added.");
		}
		else {
			request.setAttribute("errorMessage", errorMessage);

		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/listEmployees");
		rd.forward(request, response);
		return;

	}

}
