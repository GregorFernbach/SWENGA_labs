/**
 * 
 */
package at.fh.swenga.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.EmployeeManager;
import at.fh.swenga.model.EmployeeModel;

/**
 * @author Gregor Fernbach
 */

@Repository // hey Spring that is a class that knows how to handle data -> now Spring knows
			// that class and Autowired works
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session") // look in the shit that is called session other
																	// create it --- block
// employeelist from user1 would be seen by user2 when the Proxy would not be.
// Spring creates one session for the first user. All following get proxies
// Proxy acts like a switch

@Controller
public class EmployeeManagerController {

	@Autowired
	private EmployeeManager employeeManager;

	@RequestMapping(value = { "/", "listEmployees" })
	public String showAllEmployees(Model model) {
		model.addAttribute("employees", employeeManager.getAllEmployees()); // add all employees from the
																			// employeemanager under the key "employees"
		return "listEmployees";
	}

	@RequestMapping("/fillEmployeeList")
	public String fillEmployeeList(Model model) {

		Date now = new Date();
		employeeManager.addEmployee(new EmployeeModel(1, "Max", "Mustermann", now));
		employeeManager.addEmployee(new EmployeeModel(2, "Mario", "Rossi", now));
		employeeManager.addEmployee(new EmployeeModel(3, "John", "Doe", now));
		employeeManager.addEmployee(new EmployeeModel(4, "Jane", "Doe", now));
		employeeManager.addEmployee(new EmployeeModel(5, "Maria", "Noname", now));
		employeeManager.addEmployee(new EmployeeModel(6, "Josef", "Noname", now));

		model.addAttribute("employees", employeeManager.getAllEmployees());
		return "listEmployees";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}

	// Spring 4: @RequestMapping(value = "/deleteEmployee", method =
	// RequestMethod.GET)
	@GetMapping("/deleteEmployee")
	public String delete(Model model, @RequestParam int ssn) {
		boolean isRemoved = employeeManager.remove(ssn);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Employee " + ssn + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no Employee " + ssn);
		}

		// Multiple ways to "forward"
		// return "forward:/listEmployees";
		return showAllEmployees(model);
	}

	// Spring 4: @RequestMapping(value = "/searchEmployees", method =
	// RequestMethod.POST)
	@PostMapping("/searchEmployees")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("employees", employeeManager.getFilteredEmployees(searchString));
		return "listEmployees";
	}

	// Spring 4: @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	@GetMapping("/addEmployee")
	public String showAddEmployeeForm(Model model) {
		return "editEmployee";
	}

	// Spring 4: @RequestMapping(value = "/addEmployee", method =
	// RequestMethod.POST)
	@PostMapping("/addEmployee")
	public String addEmployee(@Valid EmployeeModel newEmployeeModel, BindingResult bindingResult, Model model) {

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			// Multiple ways to "forward"
			return "forward:/listEmployees";
		}

		// Look for employee in the List. One available -> Error
		EmployeeModel employee = employeeManager.getEmployeeBySSN(newEmployeeModel.getSsn());

		if (employee != null) {
			model.addAttribute("errorMessage", "Employee already exists!<br>");
		} else {
			employeeManager.addEmployee(newEmployeeModel);
			model.addAttribute("message", "New employee " + newEmployeeModel.getSsn() + " added.");
		}

		return "forward:/listEmployees";
	}

}