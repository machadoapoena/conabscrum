package controllers.publico;

import play.mvc.With;
import controllers.BaseAdmController;
import controllers.Secure;
import controllers.Seguranca;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * HomeController.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
@With(Secure.class)
public class HomeController extends BaseAdmController {

	/** The Constant PAGINA_INDEX. */
	public static final String PAGINA_INDEX = "publico/HomeController/index.html";
	
	/** The Constant PAGINA_INDEX_DESENVOLVEDOR. */
	public static final String PAGINA_INDEX_DESENVOLVEDOR = "publico/HomeController/index.html";
	
    public static void index() {
    	
    	if (Seguranca.isGestor() || Seguranca.isGerente() || Seguranca.isScrumMaster()){
    		render(PAGINA_INDEX);
    	}
    	render(PAGINA_INDEX_DESENVOLVEDOR);
    }
}