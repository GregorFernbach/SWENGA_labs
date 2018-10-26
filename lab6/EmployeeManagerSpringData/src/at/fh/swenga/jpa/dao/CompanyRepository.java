package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.CompanyModel;
import at.fh.swenga.jpa.model.EmployeeModel;

@Repository

public interface CompanyRepository extends JpaRepository<CompanyModel, Integer> {

	@Transactional
	CompanyModel findFirstByName(String companyName);

}
