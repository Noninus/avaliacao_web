package br.com.atos.util;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import br.com.atos.model.Funcionario;

public class Util {

	/**
	 * Método que lê o arquivo e retorna ele em formarto de String
	 * formatada no encoding UTF-8
	 *
	 * @param  path local do arquivo
	 * @return Uma string com o contéudo do arquivo JSON
	 */
	public String readFile() throws IOException{
		URL employeesJSON = getClass().getResource("/employees.json");
		String path = employeesJSON.getPath();
		path = path.replaceFirst("^/(.:/)", "$1");
	    byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.forName("UTF8"));
	}
	
	/**
	 * Método que pega a lista de funcionários e separa todas as habilidades
	 * possíveis em um Set.
	 *
	 * @param  funcionarios Lista com todos os funcionarios
	 * @return Lista com todas as habildiades dos funcionários.
	 */
	public Set<String> todasHabilidadesDosFuncionarios(Funcionario[] funcionarios){
		Set<String> skillsList = new HashSet<String>();
		for (Funcionario funcionario : funcionarios) {
	  		for (String skills : funcionario.getSkills()) {
		  		skillsList.add(skills);
		    }
	    }
		return skillsList;
	}
}
