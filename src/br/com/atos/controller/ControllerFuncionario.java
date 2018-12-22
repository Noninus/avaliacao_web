package br.com.atos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import br.com.atos.model.Funcionario;
import br.com.atos.util.Util;



@Controller
public class ControllerFuncionario {
	
	/**
	 * M�todo que l� o arquivo JSON e monta a uma lista de funcion�rios
	 * que foram encontrados nesse arquivo, nesse m�todo, sem filtros.
	 *
	 * @param  model Modelo que ser� enviado para o arquivo jsp
	 * @return      P�gina jsp que mostra a lista completa
	 * @throws IOException 
	 */
	@RequestMapping(value="/")
	public String listaFuncionarios(Model model) throws IOException {
		
		Util util = new Util();
		//Leitura do arquivo JSON
		String jsonString = util.readFile();
		
		//Uso da API Gson para transformar o JSON em uma Lista de Funcionarios. 
		Funcionario[] funcionarios = new Gson().fromJson(jsonString, Funcionario[].class);
		
		//Lista de skills que constroi o filtro com todas as op��es para serem selecionadas
		Set<String> skillsList = util.todasHabilidadesDosFuncionarios(funcionarios);
		 
		  model.addAttribute("funcionarios", funcionarios);
		  model.addAttribute("skillsList", skillsList);
		  
		return "listaFuncionarios";
	}
	
	/**
	 * M�todo que l� o arquivo JSON e monta a uma lista de funcion�rios
	 * que foram encontrados nesse arquivo utilizando dos filtros
	 * escolhidos pelo usu�rio da aplica��o a lista � atualizada,
	 * sobrando apenas o que o usu�rio escolheu.
	 *
	 * @param  model Modelo que ser� enviado para o arquivo jsp
	 * @param  selecionado m�todo post da jsp enviar� uma lista de Strings
	 * com os filtros selecionados pelo usu�rio
	 * @return      P�gina jsp que mostra a lista filtrada
	 * @throws IOException 
	 */
	@RequestMapping(value="/listaFuncionariosFiltrado")
	public String listaFuncionariosFiltrado(Model model, String[] selecionado) throws IOException {
		
		if(selecionado == null) {
			return "redirect:/";
		}
		
		Util util = new Util();
		//Leitura do arquivo JSON
		String jsonString = util.readFile();
		

		//Uso da API Gson para transformar o JSON em uma Lista de Funcionarios. 
	  	Funcionario[] funcionarios = new Gson().fromJson(jsonString, Funcionario[].class);
	  	ArrayList<Funcionario> list = new ArrayList(Arrays.asList(funcionarios));
	  	List<Funcionario> toRemove = new ArrayList<Funcionario>();
	  	
		//Comando em que a lista completa de funcion�rios � filtrada.  	
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
		  	
	    //Lista de skills que constroi o filtro com todas as op��es para serem selecionadas	  	
	  	Set<String> skillsList = util.todasHabilidadesDosFuncionarios(funcionarios);
	  
	  	model.addAttribute("funcionarios", list);
	  	model.addAttribute("skillsList", skillsList);
		
		return "listaFuncionarios";
	}
	
	
}
