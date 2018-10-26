package at.fh.swenga.jpa.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;

import at.fh.swenga.jpa.dao.EmployeeRepository;
import at.fh.swenga.jpa.model.EmployeeModel;

@Controller
public class ReportController {
	@Autowired
	EmployeeRepository employeeRepository;


	@RequestMapping(value = { "/report" })
	public String report(Model model, @RequestParam(required = false) String excel,
			@RequestParam(required = false) String pdf, @RequestParam(required = false) String mail,
			@RequestParam(name = "employeeId", required = false) List<Integer> employeeIds) {

		// User didn't select any employee ? -> go back to list
		if (CollectionUtils.isEmpty(employeeIds)) {
			model.addAttribute("errorMessage", "No employees selected!");
			return "forward:/list";
		}

		// Convert the list of ids to a list of employees.
		// The method findAll() can do this
		List<EmployeeModel> employees = employeeRepository.findAllById(employeeIds);

		// Store the employees in the model, so the reports can access them
		model.addAttribute("employees", employees);

		// Which submit button was pressed? -> call the right report view
		if (StringUtils.isNoneEmpty(excel)) {
			return "excelReport";
		} else if (StringUtils.isNoneEmpty(pdf)) {
			//return "pdfReportV5";
			return "pdfReport";
		} 

		
		else {
			return "forward:/list";
		}
	}


	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}

}
