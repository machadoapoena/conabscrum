package servicos;

import java.util.List;

import models.Projeto;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * ProjetoService.java
 *
 * @date 01/08/2014
 * @author apoena.machado
 */
public interface ProjetoService {

	/**
	 * Recuperar projetos.
	 *
	 * @return the list
	 */
	public List<Projeto> recuperarProjetos();
	
	/**
	 * Recuperar projeto por id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Projeto> recuperarProjetoPorId(Long id);
	
	/**
	 * Recuperar projeto por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Projeto> recuperarProjetoPorGerente(String login);
	
	/**
	 * Recuperar projeto por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Projeto> recuperarProjetoPorScrumMaster(String login);
	
	/**
	 * Recuperar projeto por desenvolvedor.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Projeto> recuperarProjetoPorDesenvolvedor(String login);
}
