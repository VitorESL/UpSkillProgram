package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects= proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";		
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { //@RequestParam List<Long> employees
		
		proRepo.save(project);
		
		/*
		 * from here on is how we make possible to associate the employeeId to a chosen
		 * project (as its foreign key, if this was a many to one relationship)
		 * 
		 * Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		 * 
		 * for (Employee employee : chosenEmployees) { employee.setTheProject(project);
		 * empRepo.save(employee); }
		 */
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
		
		
	}
	
	
	
}
