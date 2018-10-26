package at.fh.swenga.Calculator;

import java.io.IOException;

import javax.servlet.RequestDispatcher; // interface (most generic version) -> not the apache implementation -
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/Calculator") //availbe under the http path 
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num1Param = request.getParameter("num1"); // num1 is the unique identifier
		String num2Param = request.getParameter("num2");
 
 //EXCEPTION HANDLING!!! This is a block (TRY-BLOCK). Java is block oriented
		try {
			int num1 = Integer.parseInt(num1Param);
			int num2 = Integer.parseInt(num2Param);
 
			//BUSINESS LOGIC!!!
			int result = num1 + num2;
		// how to store data for the next process to retrieve data again
			request.setAttribute("result", result); // we have to save this so that it is available outside of the block
			
		//how to get data forward to the next component
			RequestDispatcher rq = request.getRequestDispatcher("./showResult.jsp"); //inversion of control
			rq.forward(request, response);
			return;
				//This is the mother class for all exceptions -> It catches all Exceptions that happen!
		} catch (Exception e) {
			e.printStackTrace(); // Long bunch of red output
			request.setAttribute("error", "There was something wrong "+e.getMessage());
			RequestDispatcher rq = request.getRequestDispatcher("./showError.jsp");
			rq.forward(request, response);
		}
 
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
