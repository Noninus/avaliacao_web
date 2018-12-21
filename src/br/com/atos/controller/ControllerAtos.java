package br.com.atos.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
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
	public String cart(HttpSession session, Model model) {
		Util util = new Util();
		String jsonString = "";
		try {
			//falta pegar do arquivo do projeto
			jsonString = util.readFile("C:\\employees.json", Charset.forName("UTF8"));
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
	
}
