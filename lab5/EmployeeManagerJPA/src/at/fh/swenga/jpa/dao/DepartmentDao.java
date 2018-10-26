package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.Department;
 
@Repository
@Transactional
public class DepartmentDao {
 
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<Department> getDepartments() {
 
		TypedQuery<Department> typedQuery = entityManager.createQuery(
				"select d from Department d", Department.class);
		List<Department> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public Department getDepartment(String name) {
		try {
 
			TypedQuery<Department> typedQuery = entityManager.createQuery(
					"select d from Department d where d.name = :name",
					Department.class);
			typedQuery.setParameter("name", name);
			Department department = typedQuery.getSingleResult();
			return department;
		} catch (NoResultException e) {
			return null;
		}
	}
 
}