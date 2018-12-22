package br.com.atos.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import br.com.atos.model.Funcionario;
import br.com.atos.util.Util;



@Controller
public class ControllerAtos {
	
	@RequestMapping(value="/")
	public String listaFuncionarios(HttpSession session, Model model) {
		Util util = new Util();
		String jsonString = "";
		try {
			jsonString = util.readFile("c://employees.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  	Funcionario[] funcionarios = new Gson().fromJson(jsonString, Funcionario[].class);
		  	
		  	Set<String> skillsList = new HashSet<String>();
		  	for (Funcionario funcionario : funcionarios) {
		  		for (String skills : funcionario.getSkills()) {
			  		skillsList.add(skills);
			    }
		    }
		  
		  	model.addAttribute("funcionarios", funcionarios);
		  	model.addAttribute("skillsList", skillsList);
		
		return "listaFuncionarios";
	}
	
	
	@RequestMapping(value="/listaFuncionariosFiltrado")
	public String listaFuncionariosFiltrado(Model model, String[] selecionado) {
		if(selecionado == null) {
			return "redirect:/";
		}
		Util util = new Util();
		String jsonString = "";
		try {
			jsonString = util.readFile("c://employees.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  	Funcionario[] funcionarios = new Gson().fromJson(jsonString, Funcionario[].class);
		  	ArrayList<Funcionario> list = new ArrayList(Arrays.asList(funcionarios));
		  	List<Funcionario> toRemove = new ArrayList<Funcionario>();
		  	
		  		boolean funcionarioFiltro;
		  		for (Funcionario funcionario : funcionarios) {
		  			funcionarioFiltro = false;
			  		for (String skills : funcionario.getSkills()) {
			  			for (String selecionados : selecionado) {
					  		if(skills.equals(selecionados)) {
					  			funcionarioFiltro = true;
					  		}
					    }
				    }		
			  		if(!funcionarioFiltro) {
			  			toRemove.add(funcionario);
			  		}
			    }	
			  	list.removeAll(toRemove);  	
		  	
		  		  	
		  	Set<String> skillsList = new HashSet<String>();
		  	for (Funcionario funcionario : funcionarios) {
		  		for (String skills : funcionario.getSkills()) {
			  		skillsList.add(skills);
			    }
		    }
		  
		  	model.addAttribute("funcionarios", list);
		  	model.addAttribute("skillsList", skillsList);
		
		return "listaFuncionarios";
	}
	
}
