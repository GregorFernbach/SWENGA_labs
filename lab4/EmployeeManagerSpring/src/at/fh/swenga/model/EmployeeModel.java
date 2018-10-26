package at.fh.swenga.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeModel {
	private int ssn;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date dayOfBirth;
	
	
	public EmployeeModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EmployeeModel(int ssn, String firstName, String lastName, Date dayOfBirth) {
		super(); //ruft den constructor der mutterklasse auf wichtig bei extends
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dayOfBirth = dayOfBirth;
	}





	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
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
	public Date getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ssn;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeModel other = (EmployeeModel) obj;
		if (ssn != other.ssn)
			return false;
		return true;
	}



	
	
	
	
}
