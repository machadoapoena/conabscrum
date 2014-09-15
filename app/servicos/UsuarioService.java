package servicos;

import java.util.List;

import models.Usuario;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * UsuarioService.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public interface UsuarioService {


	/**
	 * Recupera usuarios sem atividade.
	 *
	 * @return the list
	 */
	public List<Usuario> recuperaUsuariosSemAtividade();
	
	/**
	 * Recupera usuarios sem atividade com qtd finalizadas.
	 *
	 * @return the list
	 */
	public List<Usuario> recuperaUsuariosSemAtividadeComQtdFinalizadas();
	
	/**
	 * Recupera usuarios logado.
	 *
	 * @param login the login
	 * @return the list
	 */
	public Usuario recuperaUsuarioLogado(final String login);
}
