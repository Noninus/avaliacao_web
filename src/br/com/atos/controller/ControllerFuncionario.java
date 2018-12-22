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
	 * Método que lê o arquivo JSON e monta a uma lista de funcionários
	 * que foram encontrados nesse arquivo, nesse método, sem filtros.
	 *
	 * @param  model Modelo que será enviado para o arquivo jsp
	 * @return      Página jsp que mostra a lista completa
	 * @throws IOException 
	 */
	@RequestMapping(value="/")
	public String listaFuncionarios(Model model) throws IOException {
		
		Util util = new Util();
		//Leitura do arquivo JSON
		String jsonString = util.readFile();
		
		//Uso da API Gson para transformar o JSON em uma Lista de Funcionarios. 
		Funcionario[] funcionarios = new Gson().fromJson(jsonString, Funcionario[].class);
		
		//Lista de skills que constroi o filtro com todas as opções para serem selecionadas
		Set<String> skillsList = util.todasHabilidadesDosFuncionarios(funcionarios);
		 
		  model.addAttribute("funcionarios", funcionarios);
		  model.addAttribute("skillsList", skillsList);
		  
		return "listaFuncionarios";
	}
	
	/**
	 * Método que lê o arquivo JSON e monta a uma lista de funcionários
	 * que foram encontrados nesse arquivo utilizando dos filtros
	 * escolhidos pelo usuário da aplicação a lista é atualizada,
	 * sobrando apenas o que o usuário escolheu.
	 *
	 * @param  model Modelo que será enviado para o arquivo jsp
	 * @param  selecionado método post da jsp enviará uma lista de Strings
	 * com os filtros selecionados pelo usuário
	 * @return      Página jsp que mostra a lista filtrada
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
	  	
		//Comando em que a lista completa de funcionários é filtrada.  	
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
		  	
	    //Lista de skills que constroi o filtro com todas as opções para serem selecionadas	  	
	  	Set<String> skillsList = util.todasHabilidadesDosFuncionarios(funcionarios);
	  
	  	model.addAttribute("funcionarios", list);
	  	model.addAttribute("skillsList", skillsList);
		
		return "listaFuncionarios";
	}
	
	
}
