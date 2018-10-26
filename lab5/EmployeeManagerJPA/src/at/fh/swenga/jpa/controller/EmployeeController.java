package at.fh.swenga.jpa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.DepartmentDao;
import at.fh.swenga.jpa.dao.EmployeeDao;
import at.fh.swenga.jpa.dao.ProjectDao;
import at.fh.swenga.jpa.model.Address;
import at.fh.swenga.jpa.model.Department;
import at.fh.swenga.jpa.model.EmployeeModel;
import at.fh.swenga.jpa.model.Project;

@Controller // Spring knows that this is a class to handle HTTP Requests
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	ProjectDao projectDao;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<EmployeeModel> employees = employeeDao.getEmployees();
		List<Department> departments = departmentDao.getDepartments();
		List<Project> projects = projectDao.getProjects();

		model.addAttribute("employees", employees);
		model.addAttribute("departments", departments);
		model.addAttribute("projects", projects);

		return "index";
	}


	@RequestMapping("/fillEmployeeList")
	@Transactional
	public String fillData(Model model) {

		Address address1 = new Address("At Home 1", "Graz", "8010", "Austria");
		Address address2 = new Address("Alte Poststraße 147", "Graz", "8010", "Austria");
		Address address3 = new Address("Rathausplatz 1", "Wien", "1010", "Austria");

		Address deliveryAddress1 = new Address("At Office 1", "Graz", "8010", "Austria");
		Address deliveryAddress2 = new Address("Neue Poststraße 1", "Graz", "8010", "Austria");
		Address deliveryAddress3 = new Address("Rathausplatz 1", "Wien", "1010", "Austria");

		Department departmentIT = departmentDao.getDepartment("IT");
		if (departmentIT == null)
			departmentIT = new Department("IT");

		Department departmentHR = departmentDao.getDepartment("HR");
		if (departmentHR == null)
			departmentHR = new Department("HR");

		Project project1 = projectDao.getProject("Project 1");
		if (project1 == null)
			project1 = new Project("Project 1");

		Project project2 = projectDao.getProject("Project 2");
		if (project2 == null)
			project2 = new Project("Project 2");

		Project project3 = projectDao.getProject("Project 3");
		if (project3 == null)
			project3 = new Project("Project 3");

		Project project4 = projectDao.getProject("Project 4");
		if (project4 == null)
			project4 = new Project("Project 4");

		Date now = new Date();

		EmployeeModel p1 = new EmployeeModel("Johann", "Blauensteiner", now);
		p1.setBillingAddress(address1);
		p1.setDeliveryAddress(deliveryAddress1);
		p1.setDepartment(departmentIT);

		p1.addProject(project1);
		p1.addProject(project2);
		p1.addProject(project3);

		employeeDao.persist(p1);

		EmployeeModel p2 = new EmployeeModel("Max", "Mustermann", now);
		p2.setBillingAddress(address2);
		p2.setDeliveryAddress(deliveryAddress2);
		p2.setDepartment(departmentHR);

		p2.addProject(project1);
		p2.addProject(project2);
		p2.addProject(project4);

		employeeDao.persist(p2);

		EmployeeModel p3 = new EmployeeModel("Jane", "Doe", now);
		p3.setBillingAddress(address3);
		p3.setDeliveryAddress(deliveryAddress3);
		p3.setDepartment(departmentHR);

		p3.addProject(project2);
		p3.addProject(project3);
		p3.addProject(project4);

		employeeDao.persist(p3);

		return "forward:list"; // show index with all new data -> replaces the old shitty way
								// (create model, request dispatcher, etc)
		// also possible: return index(model);
	}

	@RequestMapping("/searchEmployees")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("employees", employeeDao.searchEmployees(searchString));
		model.addAttribute("departments", departmentDao.getDepartments());
		model.addAttribute("projects", projectDao.getProjects());
		return "index";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		employeeDao.delete(id);

		return "forward:list";
	}

	// @ExceptionHandler(Exception.class) //DONT FORGET TO ENBALE IT FOR HOMEWORK
	public String handleAllException(Exception ex) {

		return "error";

	}
}