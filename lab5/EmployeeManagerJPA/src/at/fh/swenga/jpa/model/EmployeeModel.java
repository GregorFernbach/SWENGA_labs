package at.fh.swenga.jpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity // Spring from now an take care of this object
@Table(name = "Employee") // and you can store it in the database with the name Employee (otherwise it
							// would be called EmployeeModel)
public class EmployeeModel implements java.io.Serializable {

	@Id // treated as primary key
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // i don't care how the pk is created -> ask the db
	private int id;

	@Column(nullable = false, length = 30) // modify because the default: conversion in db is to varchar(250)
	String firstName;

	@Column(nullable = false, length = 30) // modify because the default: conversion in db is to varchar(250)
	String lastName;

	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	Date dayOfBirth;

	@Embedded // here the embedded Address object is injected
	Address billingAddress; // happy with default settings

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
			@AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
			@AttributeOverride(name = "country", column = @Column(name = "delivery_country")),
			@AttributeOverride(name = "zip", column = @Column(name = "delivery_zip")) })
	Address deliveryAddress; // override attributes

	@ManyToOne(cascade = CascadeType.PERSIST)
	Department department;

	//Alternative to @ManyToMany
	/*
	 * @JoinTable( name="EMP_PROJ", joinColumns={
	 * 
	 * @JoinColumn(name="EMP_ID", referencedColumnName="ID")},
	 * inverseJoinColumns={@JoinColumn(name="PROJ_ID", referencedColumnName="ID")})
	 */
	@ManyToMany(cascade = CascadeType.PERSIST) //default
	private List<Project> projects;

	@Version // for optimistic locking -> concurrency (checking versions) THIS IS ONLY FOR
				// THE DATABASE
	// JAVA DOES NOT CARE ABOUT THIS
	long version;

	public EmployeeModel() {
	}

	public EmployeeModel(String firstName, String lastName, Date dayOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dayOfBirth = dayOfBirth;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
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

	/**
	 * @return the billingAddress
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress
	 *            the billingAddress to set
	 */
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the deliveryAddress
	 */
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress
	 *            the deliveryAddress to set
	 */
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void addProject(Project project) {
		if (projects == null) {
			projects = new ArrayList<Project>();
		}
		projects.add(project);
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

}