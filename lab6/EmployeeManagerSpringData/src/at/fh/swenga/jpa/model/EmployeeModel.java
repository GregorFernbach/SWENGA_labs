package at.fh.swenga.jpa.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Employee")

@NamedQueries({
	@NamedQuery(name = "EmployeeModel.doANameSearchWithLike", query = "select e from EmployeeModel e where e.firstName like :search or e.lastName like :search")
})

public class EmployeeModel implements java.io.Serializable {


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String firstName;
	 
	@Column(nullable = false, length = 30)
	private String lastName;
	 
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	private Calendar dayOfBirth;

	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private CompanyModel company;

	
	public EmployeeModel() {
	}

	public EmployeeModel(String firstName, String lastName, Calendar dayOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dayOfBirth = dayOfBirth;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getDayOfBirth() {
		return dayOfBirth;
	}
	
	public void setDayOfBirth(Calendar dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	
	public CompanyModel getCompany() {
		return company;
	}

	public void setCompany(CompanyModel company) {
		this.company=company;
	}

	
}
