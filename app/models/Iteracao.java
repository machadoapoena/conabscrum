package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import anotacoes.JsonExclude;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.DateUtil;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Iteracao.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@Entity
@Table(name="tb_iteracao")
public class Iteracao extends Model {
	
	@PostLoad
	public void carregarAtributosTransient(){
	    this.nomeProjeto = projeto.toString();
	    this.dataFinalFormatad = DateUtil.formatarData(this.dataFinal, DateUtil.DATA_FORMATACAO_BR);
	}

	/** The nome. */
	@Required
	@Unique
	@MinSize(3)
	@MaxSize(100)
	@Column(name="nome")
	public String nome;
	
	/** The descricao. */
	@Required
	@MinSize(10)
	@MaxSize(255)
	@Column(name="descricao")
	public String descricao;
	
	/** The data inicial. */
	@Required
	@Temporal(value=TemporalType.DATE)
	@Column(name="data_inicial")
	public Date dataInicial;
	
	/** The data final. */
	@Required
	@Temporal(value=TemporalType.DATE)
	@Column(name="data_final")
	public Date dataFinal;
	
	/** The objetivo. */
	@Required
	@MinSize(10)
	@MaxSize(255)
	@Column(name="objetivo")
	public String objetivo;
	
	/** The ativo. */
	@Required
	@Column(name="ativo")
	public boolean ativo;
	
	/** The projeto. */
	@JsonExclude
	@ManyToOne
	@JoinColumn(name = "projeto_fk")
	@Required
	public Projeto projeto;
	
	@JsonExclude	
	@OneToMany(mappedBy="iteracao",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Backlog> backLogs;
	
	@Expose
	@Transient
	public int qtdDiasRestantesFim;
	
	@Expose
	@Transient
	public String nomeProjeto;
	
	@Expose
	@Transient
	public String dataFinalFormatad;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Getters e Setters
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getDataFinalFormatad() {
		return this.dataFinalFormatad;
	}
	
	public void setDataFinalFormatad(String dataFinalFormatad) {
		this.dataFinalFormatad = dataFinalFormatad;
	}
	
	public int getQtdDiasRestantesFim() {
		return this.qtdDiasRestantesFim;
	}

	public void setQtdDiasRestantesFim(int qtdDiasRestantesFim) {
		this.qtdDiasRestantesFim = qtdDiasRestantesFim;
	}
	
	public String getNomeProjeto() {
		return this.nomeProjeto;
	}
	
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera iteracao por gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Iteracao> recuperaIteracaoPorGerente(String login){
		return find("projeto.gerente.login = ?1 order by nome", login).fetch();
	}
	
	/**
	 * Recupera iteracao por scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Iteracao> recuperaIteracaoPorScrumMaster(String login){
		return find("projeto.scrumMaster.login = ?1 order by nome", login).fetch();
	}	
	
	/**
	 * Recupera iteracao por data final entre datas.
	 *
	 * @param dataInicial the data inicial
	 * @param dataFinal the data final
	 * @return the list
	 */
	public static List<Iteracao> recuperaIteracaoPorDataFinalEntreDatas(Date dataInicial, Date dataFinal){
		return find("dataFinal between ?1 and ?2", dataInicial, dataFinal).fetch();
	}
	
	/**
	 * Recupera iteracao por projeto.
	 *
	 * @param idProjeto the id projeto
	 * @return the list
	 */
	public static List<Iteracao> recuperaIteracaoPorProjeto(Long idProjeto){
		return find("projeto.id = ?1 order by nome", idProjeto).fetch();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Metodos superclasse
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#toString()
	 */
	@Override
	public String toString() {
		return this.nome;
	}
}