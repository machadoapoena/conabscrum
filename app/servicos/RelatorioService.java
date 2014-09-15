package servicos;

import java.util.List;

import models.VwProjetoAtividadeQuantidade;
import vos.RelatorioProjetosPorGerenteVO;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * RelatorioService.java
 *
 * @date 15/08/2014
 * @author apoena.machado
 */
public interface RelatorioService {

	
	/**
	 * Recuperar dados projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<RelatorioProjetosPorGerenteVO> recuperarDadosProjetosPorGerente(String login);
	
	/**
	 * Recuperar dados projetos atividade quantidade.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<VwProjetoAtividadeQuantidade> recuperarDadosProjetosAtividadeQuantidade(String login);
}