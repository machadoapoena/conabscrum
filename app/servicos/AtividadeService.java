package servicos;

import java.util.List;

import dominio.TipoSituacaoAtividadeEnum;

import models.Atividade;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * AtividadeService.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public interface AtividadeService {

	/**
	 * Recupera atividades em progresso.
	 *
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesEmProgresso();
	
	/**
	 * Recupera todas atividades sem usuario.
	 *
	 * @return the list
	 */
	public List<Atividade> recuperaTodasAtividadesSemUsuario();
	
	/**
	 * Recupera atividades de usuario.
	 *
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesDoUsuario(String usuario);
	
	/**
	 * Recupera qtd atividades finalizadas do usuario.
	 *
	 * @param login the login
	 * @return the long
	 */
	public Long recuperaQtdAtividadesFinalizadasDoUsuario(String login);
	
	/**
	 * Recupera atividades de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades a fazer de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesAFazerDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades em progresso de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesEmProgressoDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades impedido de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesImpedidoDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades finalizada de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesFinalizadaDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades a fazer de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesAFazerDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades em progresso de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesEmProgressoDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades impedido de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesImpedidoDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades finalizada de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesFinalizadaDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades sem usuario de projetos por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesSemUsuarioDeProjetosPorGerente(String login);
	
	/**
	 * Recupera atividades sem usuario de projetos por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesSemUsuarioDeProjetosPorScrumMaster(String login);
	
	/**
	 * Recupera atividades de iteracao.
	 *
	 * @param idIteracao the id iteracao
	 * @return the list
	 */
	public List<Atividade> recuperaAtividadesDeIteracao(Long idIteracao);
	
	/**
	 * Atualiza situacao.
	 *
	 * @param idAtividade the id atividade
	 * @param situacao the situacao
	 */
	public void atualizaSituacao(Long idAtividade, TipoSituacaoAtividadeEnum situacao);
}