package at.fh.swenga.jpa.model;
 
import java.util.List;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
 
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private String name;
 
	@ManyToMany(mappedBy = "projects",fetch=FetchType.EAGER)
	private List<EmployeeModel> employees;
 
	public Project() {
	}
 
	public Project(String name) {
		super();
		this.name = name;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public List<EmployeeModel> getEmployees() {
		return employees;
	}
 
	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
 
}