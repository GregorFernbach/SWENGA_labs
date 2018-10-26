package at.fh.swenga.jpa.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.CompanyRepository;
import at.fh.swenga.jpa.dao.EmployeeRepository;
import at.fh.swenga.jpa.model.CompanyModel;
import at.fh.swenga.jpa.model.EmployeeModel;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		List<EmployeeModel> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("count", employees.size());
		return "index";
	}
	
	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page,Model model) {
		Page<EmployeeModel> employeesPage = employeeRepository.findAll(page);
		model.addAttribute("employeesPage", employeesPage);
		model.addAttribute("employees", employeesPage.getContent());
		model.addAttribute("count", employeesPage.getTotalElements());
		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @RequestParam String searchType) {
		List<EmployeeModel> employees = null;
		int count=0;

		switch (searchType) {
		case "query1":
			employees = employeeRepository.findAll();
			break;
		case "query2":
			employees = employeeRepository.findByLastName(searchString);
			break;
		case "query3":
			employees = employeeRepository.findByFirstName(searchString);
			break;
		case "query4":
			employees = employeeRepository.findByWhateverName(searchString);
			break;
		case "query5":
			employees = employeeRepository.doANameSearchWithLike("%"+searchString+"%");
			break;
		case "query6":
			count=employeeRepository.countByLastName(searchString);		
			break;
		case "query7":
			employees=employeeRepository.removeByLastName(searchString);		
			break;
		case "query8":
			count=employeeRepository.deleteByCompanyName(searchString);		
			break;
		case "query9":
			employees=employeeRepository.findByLastNameContainingOrFirstNameContainingAllIgnoreCase(searchString,searchString);		
			break;
		case "query10":
			employees=employeeRepository.findByOrderByLastNameAsc();		
			//employees=employeeRepository.findAllByOrderByLastNameAsc();		
			break;
		case "query11":
			employees=employeeRepository.findTop10ByOrderByLastName();		
			break;

		case "query12":
			employees=employeeRepository.findByCompanyNameOrderByLastNameAsc(searchString);		
			break;
		case "query13":
			Calendar nowMinus40 = Calendar.getInstance();
			nowMinus40.add(Calendar.YEAR, -40);
			employees = employeeRepository.findByDayOfBirthAfter(nowMinus40);
			break;
		case "query14":
			Calendar year1980 = Calendar.getInstance();
			year1980.set(1980, 0, 1);
			Calendar year1985 = Calendar.getInstance();
			year1985.set(1985, 11,31);
			employees = employeeRepository.findByDayOfBirthBetween(year1980,year1985);
			break;
		case "query15":
			employees = employeeRepository.findByCompanyName(searchString);
			break;

		default:
			employees = employeeRepository.findAll();
		}
		
		model.addAttribute("employees", employees);

		if (employees!=null) {
			model.addAttribute("count", employees.size());			
		}
		else {
			model.addAttribute("count", count);				
		}
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") EmployeeModel e, Model model) {
		if (e!=null) {
			List<EmployeeModel> employees = new ArrayList<EmployeeModel>();
			employees.add(e);
			model.addAttribute("employees", employees);
		}
		return "index";
	}



	
	@RequestMapping("/fillEmployeeList")
	@Transactional
	public String fillData(Model model) {

		DataFactory df = new DataFactory();
		
		CompanyModel company = null;
		
		for(int i=0;i<100;i++) {
			if (i%10==0) {
				String companyName=df.getBusinessName();
				company=companyRepository.findFirstByName(companyName);
				if (company==null) {
					company = new CompanyModel(companyName);
				}
			}
			
			Calendar dob = Calendar.getInstance();
			dob.setTime(df.getBirthDate());
			
			EmployeeModel employeeModel = new EmployeeModel(df.getFirstName(),df.getLastName(),dob);
			employeeModel.setCompany(company);
			employeeRepository.save(employeeModel);
		}
	
		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		employeeRepository.deleteById(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}
