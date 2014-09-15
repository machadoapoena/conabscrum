package controllers;

import controllers.publico.HomeController;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * BaseAdmController.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public class BaseAdmController extends GenericControle {

	/** The Constant IDENTIFICADOR_PROJETO_ID. */
	public static final String IDENTIFICADOR_PROJETO_ID = "identificadorProjetoId";
	
	/**
	 * Seleciona projeto.
	 *
	 * @param id the id
	 */
	public static void selecionaProjeto(Long id) {
		session.put(IDENTIFICADOR_PROJETO_ID, id);
		redirect("publico.HomeController.index");
    }
}
