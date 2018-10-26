package at.fh.swenga.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.WebContext;

import at.fh.swenga.model.EmployeeManager;

/**
 * Servlet implementation class ListEmployees
 */
@WebServlet("/")
public class ListEmployees extends ThymeleafServlet {
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
		HttpSession session = request.getSession(true);
		EmployeeManager employeeManager =(EmployeeManager)session.getAttribute("employeeManager");
		
		if (employeeManager==null) {
			employeeManager=new EmployeeManager();
			session.setAttribute("employeeManager", employeeManager);
		}
		
		request.setAttribute("employees", employeeManager.getAllEmployees());

        WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
        String templateName = "/listEmployees.html";
        
        request.setAttribute("active", templateName);
        
        String result = engine.process(templateName, ctx);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(result);
        } finally {
            out.close();
        }
	}

}
