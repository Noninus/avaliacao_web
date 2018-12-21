package br.com.atos.model;

import java.math.BigDecimal;
import java.util.Date;

public class Projects {

	String name;
	String customer;
	BigDecimal valueOfProject;
	Date dtBegin;
	Date dtEnd;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public BigDecimal getValueOfProject() {
		return valueOfProject;
	}
	public void setValueOfProject(BigDecimal valueOfProject) {
		this.valueOfProject = valueOfProject;
	}
	public Date getDtBegin() {
		return dtBegin;
	}
	public void setDtBegin(Date dtBegin) {
		this.dtBegin = dtBegin;
	}
	public Date getDtEnd() {
		return dtEnd;
	}
	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}
	
	
}
