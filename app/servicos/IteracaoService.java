package servicos;

import java.util.List;

import models.Iteracao;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * IteracaoService.java
 *
 * @date 05/08/2014
 * @author apoena.machado
 */
public interface IteracaoService {

	/**
	 * Recupera iteracoes vencendo.
	 *
	 * @return the list
	 */
	public List<Iteracao> recuperaIteracoesVencendo(int qtdDias);
	
	/**
	 * Recupera iteracoes do projeto.
	 *
	 * @param idProjeto the id projeto
	 * @return the list
	 */
	public List<Iteracao> recuperaIteracoesDoProjeto(Long idProjeto);
}
