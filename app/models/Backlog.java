package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Backlog.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@Entity
@Table(name="tb_backlog")
public class Backlog extends Model {

	/** The nome. */
	@Required
	@Unique
	@MinSize(3)
	@MaxSize(100)
	@Column(name="titulo")
	public String titulo;
	
	/** The descricao. */
	@Required
	@MinSize(10)
	@MaxSize(255)
	@Column(name="descricao")
	public String descricao;
	
	/** The ativo. */
	@Required
	@Column(name="ativo")
	public boolean ativo;
	
	/** The iteracao. */
	@Required
	@ManyToOne
	@JoinColumn(name = "iteracao_fk")
	public Iteracao iteracao;
	
	@OneToMany(mappedBy="backLog",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Atividade> atividades;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera backlogs por login gerente.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Backlog> recuperaBacklogsPorLoginGerente(String login){
		return find("iteracao.projeto.gerente.login = ?1", login).fetch();
	}
	
	/**
	 * Recupera backlogs por login scrum master.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Backlog> recuperaBacklogsPorLoginScrumMaster(String login){
		return find("iteracao.projeto.scrumMaster.login = ?1", login).fetch();
	}
	
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#toString()
	 */
	@Override
	public String toString() {
		return this.titulo;
	}
}