package at.fh.swenga.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.EmployeeModel;

@Repository //introduce this to Spring, if I ask for this DAO I want to get it and don't care how to
@Transactional //enables transaction handling -> can also be put in front of every method
public class EmployeeDao {

	@PersistenceContext // no @Autowired but @PersistenceContext -> we ask Spring for all this crazy shit
	protected EntityManager entityManager;

	public List<EmployeeModel> getEmployees() {
		TypedQuery<EmployeeModel> typedQuery = entityManager.createQuery("select e from EmployeeModel e",
				EmployeeModel.class); //JPQL version
		List<EmployeeModel> typedResultList = typedQuery.getResultList(); //returns List which can only hold EmployeeModel(s)
		return typedResultList;
	}

	public List<EmployeeModel> searchEmployees(String searchString) {
		TypedQuery<EmployeeModel> typedQuery = entityManager.createQuery(
				"select e from EmployeeModel e where e.firstName like :search or e.lastName like :search",
				EmployeeModel.class); //everything after : is a placeholder
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<EmployeeModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public EmployeeModel getEmployee(int i) throws DataAccessException {
		return entityManager.find(EmployeeModel.class, i); //Select
	}

	public void persist(EmployeeModel employee) {
		entityManager.persist(employee); //persist == insert into
	}

	public EmployeeModel merge(EmployeeModel employee) {
		return entityManager.merge(employee); //merge == update if it does not work use persist
	}

	public void delete(EmployeeModel employee) {
		entityManager.remove(employee); //delete
	}

	public int deleteAllEmployees() {
		int count = entityManager.createQuery("DELETE FROM EmployeeModel").executeUpdate();
		return count;
	}

	public void delete(int id) {
		EmployeeModel employee = getEmployee(id);
		if (employee != null)
			delete(employee);
	}
}