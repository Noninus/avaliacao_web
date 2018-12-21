package br.com.atos.model;

import java.math.BigDecimal;
import java.util.List;

public class Funcionario {

	String name;
	String role;
	String salary;
	String manager;
	String gcm;
	Projects[] projects;
	String[] skills;
	String[] certification;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getGcm() {
		return gcm;
	}
	public void setGcm(String gcm) {
		this.gcm = gcm;
	}
	public Projects[] getProjects() {
		return projects;
	}
	public void setProjects(Projects[] projects) {
		this.projects = projects;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	public String[] getCertification() {
		return certification;
	}
	public void setCertification(String[] certification) {
		this.certification = certification;
	}
	
	

	
}
