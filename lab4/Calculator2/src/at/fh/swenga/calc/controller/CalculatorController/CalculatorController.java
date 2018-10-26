package at.fh.swenga.calc.controller.CalculatorController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {

	@RequestMapping("/")
	public String index() {
		// returns the view name
		return "index"; //Replacement for the whole request dispatcher block
	}

	@RequestMapping("/calc") //it is also possible to overrule different namings in the Requestparameter "Number1"
	public String calc(@RequestParam int num1, @RequestParam int num2, Model model) {

		model.addAttribute("result", num1 + num2);
		// returns the view name
		return "result";
	}
	
	@ExceptionHandler(Exception.class) //Exceptionhandler for all exceptions
	public String handleAllException(Exception ex) {
 
		return "showError";
 
	}
	/** Example for a different Exception handler
	@ExceptionHandler(Exception.sqlException) //Exceptionhandler for all exceptions
	public String handleAllException(Exception ex) {
 
		return "showError";
 
	}
	**/
	
	@RequestMapping("/calcV2")
	public ModelAndView calcV2(@RequestParam int num1, @RequestParam int num2) {
 
		ModelAndView mav = new ModelAndView("result", "result", num1 + num2);
		return mav;
	}
 
	@RequestMapping("/calcV3")
	public ModelAndView calcV3(@RequestParam int num1, @RequestParam int num2) {
 
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", num1 + num2);
		mav.setViewName("result");
		return mav;
	}
 
	/**
	 * Old style still possible
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/calcV4")
	public ModelAndView calcV4(HttpServletRequest request) {
 
		String num1String = request.getParameter("num1");
		String num2String = request.getParameter("num2");
 
		int num1 = Integer.parseInt(num1String);
		int num2 = Integer.parseInt(num2String);
 
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", num1 + num2);
		mav.setViewName("result");
		return mav;
	}

}
