package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.Project;
 
@Repository
@Transactional
public class ProjectDao {
 
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<Project> getProjects() {
 
		TypedQuery<Project> typedQuery = entityManager.createQuery(
				"select p from Project p", Project.class);
		List<Project> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public Project getProject(String name) {
		try {
			TypedQuery<Project> typedQuery = entityManager.createQuery(
					"select p from Project p where p.name = :name",
					Project.class);
			typedQuery.setParameter("name", name);
			Project project = typedQuery.getSingleResult();
			return project;
		} catch (NoResultException e) {
			return null;
		}
 
	}
}