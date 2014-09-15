package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import anotacoes.JsonExclude;

import com.google.gson.annotations.Expose;

import dominio.TipoAtividadeEnum;
import dominio.TipoSituacaoAtividadeEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 * 
 * Atividade.java
 *
 * @author apoena.machado
 * @date 29/07/2014
 */
@Entity
@Table(name="tb_atividade")
public class Atividade extends Model {

	@PostLoad
	public void carregarAtributosTransient(){
	    this.nomeProjeto = backLog.iteracao.projeto.toString();
	    this.nomeUsuario = usuario != null ? usuario.toString() : null;
	}
	
	@Required
	@Unique
	@MinSize(3)
	@MaxSize(100)
	@Column(name="titulo")
	public String titulo;
	
	@Required
	@MinSize(10)
	@MaxSize(255)
	@Column(name="descricao")
	public String descricao;
	
	@Required
	@Column(name="ativo")
	public boolean ativo;
	
	@Required
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_atividade")
	public TipoAtividadeEnum tipoAtividade;
	
	@Required
	@Enumerated(EnumType.ORDINAL)
	@Column(name="situacao")
	public TipoSituacaoAtividadeEnum situacao;
	
	@ManyToOne
	@JoinColumn(name = "backlog_fk")
	@Required
	@JsonExclude
	public Backlog backLog;
	
	@ManyToOne
	@JoinColumn(name = "usuario_fk")
	@JsonExclude
	public Usuario usuario;
	
	@Expose
	@Transient
	public String nomeProjeto;
	
	@Expose
	@Transient
	public String nomeUsuario;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Getters and Setters
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getNomeProjeto() {
		return this.nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera por situacao.
	 *
	 * @param situacao the situacao
	 * @return the list
	 */
	public static List<Atividade> recuperaPorSituacao(TipoSituacaoAtividadeEnum situacao){
		return find("bySituacao", situacao).fetch();
	}
	
	/**
	 * Recupera por usuario.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Atividade> recuperaPorUsuario(String login){
		return find("usurio.login = ?1", login).fetch();
	}
	
	/**
	 * Recupera quantidade atividades por usuario situacao.
	 *
	 * @param login the login
	 * @param situacao the situacao
	 * @return the long
	 */
	public static Long recuperaQuantidadeAtividadesPorUsuarioSituacao(String login, TipoSituacaoAtividadeEnum situacao){
		return count("usuario.login = ?1 AND situacao = ?2", login, situacao);
	}
	
	/**
	 * Recupera atividades por id gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesPorLoginGerente(String login){
		return find("backLog.iteracao.projeto.gerente.login = ?1", login).fetch();
	}
	
	/**
	 * Recupera atividades por situacao login gerente.
	 *
	 * @param login the login
	 * @param situacao the situacao
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesPorSituacaoLoginGerente(String login, TipoSituacaoAtividadeEnum situacao){
		return find("backLog.iteracao.projeto.gerente.login = ?1 AND situacao = ?2", login, situacao).fetch();
	}
	
	/**
	 * Recupera atividades por id scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesPorLoginScrumMaster(String login){
		return find("backLog.iteracao.projeto.scrumMaster.login = ?1", login).fetch();
	}
	
	/**
	 * Recupera atividades por situacao login scrum master.
	 *
	 * @param login the login
	 * @param situacao the situacao
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesPorSituacaoLoginScrumMaster(String login, TipoSituacaoAtividadeEnum situacao){
		return find("backLog.iteracao.projeto.scrumMaster.login = ?1 AND situacao = ?2", login, situacao).fetch();
	}
	
	/**
	 * Recupera atividades sem usuario.
	 *
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesSemUsuario(){
		return find("usuario is null").fetch();
	}
	
	/**
	 * Recupera atividades sem usuario por login gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesSemUsuarioPorLoginGerente(String login){
		return find("usuario is null AND backLog.iteracao.projeto.gerente.login = ?1", login).fetch();
	}

	/**
	 * Recupera atividades sem usuario por login scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesSemUsuarioPorLoginScrumMaster(String login){
		return find("usuario is null AND backLog.iteracao.projeto.scrumMaster.login = ?1", login).fetch();
	}
	
	/**
	 * Recupera atividades por iteracao.
	 *
	 * @param idIteracao the id iteracao
	 * @return the list
	 */
	public static List<Atividade> recuperaAtividadesPorIteracao(Long idIteracao){
		return find("backLog.iteracao.id = ?1", idIteracao).fetch();
	}
	
	
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#toString()
	 */
	@Override
	public String toString() {
		return this.titulo;
	}
}