package at.fh.swenga.jpa.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.EmployeeModel;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {
	
	List<EmployeeModel> findByLastName(String lastName);
	List<EmployeeModel> findByFirstName(String lastName);
		
	@Query("select e from EmployeeModel e where e.firstName = :name or e.lastName = :name")
	List<EmployeeModel> findByWhateverName(@Param("name") String name);

	List<EmployeeModel> doANameSearchWithLike(@Param("search")String searchString);
	
	int countByLastName(String lastname);
	
	List<EmployeeModel> removeByLastName(String lastname);
	int deleteByCompanyName(String lastname);
	
	List<EmployeeModel> findByLastNameContainingOrFirstNameContainingAllIgnoreCase(String lastName,String firstName);

	public List<EmployeeModel> findByOrderByLastNameAsc();
	public List<EmployeeModel> findAllByOrderByLastNameAsc();
	
	public List<EmployeeModel> findTop10ByOrderByLastName();
	
	public List<EmployeeModel> findByCompanyNameOrderByLastNameAsc(String name);
	
	public List<EmployeeModel> findByDayOfBirthAfter(Calendar date);
	public List<EmployeeModel> findByDayOfBirthBetween(Calendar dateFrom,Calendar dateTo);
	
	List<EmployeeModel> findByCompanyName(@Param("companyName") String companyName);
	
}
