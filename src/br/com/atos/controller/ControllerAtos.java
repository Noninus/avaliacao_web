package br.com.atos.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ControllerAtos {
	
	@RequestMapping(value="/")
	public String cart(HttpSession session, Model model) {
		
		
		
		return "listaFuncionarios";
	}
	
}