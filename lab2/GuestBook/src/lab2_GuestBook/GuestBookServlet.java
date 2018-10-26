package lab2_GuestBook;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/addEntry")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
		// -------------------------------------------------
		// get the Session for the user
		// and
		// get the ServletContext
		// -------------------------------------------------
 
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext(); // is in the base Class (HttpServlet)
 
		// -------------------------------------------------
		// get data out of request (index.jsp)
		// -------------------------------------------------
		String user = request.getParameter("user"); //name of input
		String headline= request.getParameter("headline");
		String text = request.getParameter("text");
		String scope = request.getParameter("scope");
 
		// -------------------------------------------------
		// Store data in a new guestbook object	(create a new GuestBookModel(Entry))
		// -------------------------------------------------
		GuestBookModel guestBookModel = new GuestBookModel(user,headline,text);
 
		// -------------------------------------------------
		// Try to find a GuestBook Manager
		// Such object could be in the Session or in the ServletContext
		// choose the right one based on users choice
		// -------------------------------------------------
 
		GuestBookManager gbm = null;
 
		if (scope.equals("session")) {
			gbm=(GuestBookManager)session.getAttribute("gbm");			
		} else {
			gbm=(GuestBookManager)servletContext.getAttribute("gbm");			
		}
 
		// -------------------------------------------------
		// Hm, no GuestbookManager in the desired scope (session/servletContext) available???
		// So it must be the first time -> we have to create one
		// -------------------------------------------------
		if (gbm==null) { //always use BLOCKS!!!
			gbm = new GuestBookManager();
 
			// -------------------------------------------------
			// OK, now we have a new GuestBookManager, store it for later use
			// -------------------------------------------------
			if (scope.equals("session")) {
				session.setAttribute("gbm",gbm);			
			} else {
				servletContext.setAttribute("gbm",gbm);			
			}
		}
 
		// -------------------------------------------------
		// add the new GuestBook entry to the other entries
		// GuestBookManager knows how to do it
		// -------------------------------------------------
 
		gbm.add(guestBookModel);
 
		// -------------------------------------------------
		// We are done, "forward" to the web page to display 
		// the content of both GuestBookManagers 
		// - the one in the session and  
		// - the one in the servlet context
		// -------------------------------------------------
 
		RequestDispatcher rd = request.getRequestDispatcher("/show2.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
