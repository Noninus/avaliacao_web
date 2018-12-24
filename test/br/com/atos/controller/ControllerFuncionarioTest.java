package br.com.atos.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = {ControllerFuncionario.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ControllerFuncionarioTest {

	 @Autowired
	    private WebApplicationContext wac;

	    private MockMvc mockMvc;

	    @Before
	    public void setup () {
	        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
	        this.mockMvc = builder.build();
	    }

	    @Test
	    public void testHome () throws Exception {
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/");
	        this.mockMvc.perform(builder)
	                    .andExpect(MockMvcResultMatchers.status()
                                .isOk());
	    }

	    @Test
	    public void testFilterZerado () throws Exception {
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/listaFuncionariosFiltrado");
	        this.mockMvc.perform(builder)
	        	.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	    }
	    
	    
	    @Test
	    public void testFilter () throws Exception {
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/listaFuncionariosFiltrado");
	        this.mockMvc.perform(builder)
	        	.andExpect(MockMvcResultMatchers.redirectedUrl("/listaFuncionariosFiltrado"));
	    }
}