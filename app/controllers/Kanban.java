package controllers;

import java.util.List;

import models.Atividade;
import models.Iteracao;
import models.Projeto;
import play.mvc.Controller;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Kanban.java
 *
 * @date 27/08/2014
 * @author apoena.machado
 */
public class Kanban extends BaseAdmController {

	/**
	 * Exibir quadro.
	 *
	 * @param idProjeto the id projeto
	 * @param idIteracao the id iteracao
	 */
	public static void exibirQuadro(Long idProjeto, Long idIteracao) {
		
		List<Projeto> lstProjeto = null;
		List<Iteracao> lstIteracao = null;
		List<Atividade> lstAtividade = null;
		String login = session.get("username");
		if (idProjeto != null && idIteracao != null){
			lstAtividade = atividadeService.recuperaAtividadesDeIteracao(idIteracao);
		}
		if (Seguranca.isGerente()) {
			lstProjeto = projetoService.recuperarProjetoPorGerente(login);
		} else if (Seguranca.isScrumMaster()) {
			lstProjeto = projetoService.recuperarProjetoPorScrumMaster(login);
		} else if (Seguranca.isDesenvolvedor()) {
			lstProjeto = projetoService.recuperarProjetoPorDesenvolvedor(login);
		}
		if (idProjeto != null){
			lstIteracao = iteracaoService.recuperaIteracoesDoProjeto(idProjeto);
		}
		render(lstProjeto, lstIteracao, lstAtividade, idProjeto, idIteracao);
	}
}