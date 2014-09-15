package controllers;

import play.*;
import play.modules.spring.Spring;
import play.mvc.*;
import servicos.ProjetoService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import models.*;

public class Application extends Controller {

	private static ProjetoService projetoService;
	
	@Autowired
    public void setProjetoService(ProjetoService projetoService)
    {
        this.projetoService = projetoService;
    }
	
    public static void index() {
    	ProjetoService test = Spring.getBeanOfType(ProjetoService.class);
    	projetoService.recuperarProjetos();
        render();
    }

}