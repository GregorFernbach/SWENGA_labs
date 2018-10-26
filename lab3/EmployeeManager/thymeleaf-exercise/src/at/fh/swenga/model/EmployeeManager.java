package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeManager {

	private List<EmployeeModel> employees = new ArrayList<EmployeeModel>();

	public EmployeeManager() {
		addDummyEmployees();
	}

	public void addDummyEmployees() {
		addEmployee(new EmployeeModel(1, "Max", "Mustermann", createCalendar(2001)));
		addEmployee(new EmployeeModel(2, "Mario", "Rossi", createCalendar(2002)));
		addEmployee(new EmployeeModel(3, "John", "Doe", createCalendar(1997)));
		addEmployee(new EmployeeModel(4, "Jane", "Doe", createCalendar(2000)));
		addEmployee(new EmployeeModel(5, "Maria", "Noname", createCalendar(1987)));
		addEmployee(new EmployeeModel(6, "Josef", "Noname", createCalendar(1950)));
	}
	
	private Calendar createCalendar(Integer year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 1, 1);
		return cal;
	}

	/**
	 * Add employee to List
	 * 
	 * @param employee
	 */
	public void addEmployee(EmployeeModel employee) {
		employees.add(employee);
	}

	/**
	 * Verify if list contains employee with same SSN
	 * 
	 * @param employee
	 * @return
	 */
	public boolean contains(EmployeeModel employee) {
		return employees.contains(employee);
	}

	/**
	 * convenient method: true if list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return employees.isEmpty();
	}

	/**
	 * try to find EmployeeModel with given SSN return model, otherwise null
	 * 
	 * @param ssn
	 * @return
	 */
	public EmployeeModel getEmployeebySSN(int ssn) {
		for (EmployeeModel employeeModel : employees) {
			if (employeeModel.getSsn() == ssn) {
				return employeeModel;
			}
		}
		return null;
	}

	/**
	 * return list with all employees
	 * 
	 * @return
	 */
	public List<EmployeeModel> getAllEmployees() {
		return employees;
	}

	/**
	 * return a new list with all employees where firstname or lastname contains
	 * search string
	 * 
	 * @param searchString
	 * @return
	 */
	public List<EmployeeModel> getFilteredEmployees(String searchString) {

		// List for results
		List<EmployeeModel> filteredList = new ArrayList<EmployeeModel>();

		// check every employee
		for (EmployeeModel employeeModel : employees) {

			if ((employeeModel.getFirstName() != null && employeeModel.getFirstName().contains(searchString))
					|| (employeeModel.getLastName() != null && employeeModel.getLastName().contains(searchString))) {
				filteredList.add(employeeModel);
			}
		}
		return filteredList;
	}

	/**
	 * remove employees with same ssn
	 * 
	 * @param ssn
	 * @return
	 */
	public boolean remove(int ssn) {
		return employees.remove(new EmployeeModel(ssn, null, null, null));
	}
}
