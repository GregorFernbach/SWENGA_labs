package at.fh.swenga.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;

@Entity // again another database table
public class Department {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	//Eager -> gierig sein: lies die ganze scheiﬂe gleich aus
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER) // one of this class is mapped to many of the other
																	// class
	@OrderBy("lastName, firstName")
	private Set<EmployeeModel> employees;

	@Version
	long version;

	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmployeeModel> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeModel> employees) {
		this.employees = employees;
	}

	public void addEmployee(EmployeeModel employee) {
		if (employees == null) {
			employees = new HashSet<EmployeeModel>();
		}
		employees.add(employee);
	}

}