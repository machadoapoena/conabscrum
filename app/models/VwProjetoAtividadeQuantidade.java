package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * VwProjetoAtividadeQuantidade.java
 *
 * @date 21/08/2014
 * @author apoena.machado
 */
@Entity
@Table(name="vw_projeto_atividade_quantidade")
public class VwProjetoAtividadeQuantidade extends Model {

	/** The id. */
	@Id
	@Column(name = "id")
	private Long id;
	
	/** The nome projeto. */
	@Column(name="nome_projeto")
	public String nomeProjeto;
	
	/** The id gerente. */
	@Column(name="gerente_fk")
	public Long idGerente;
	
	/** The id scrum master. */
	@Column(name="scrum_master_fk")
	public Long idScrumMaster;
	
	/** The qtd atividade total. */
	@Column(name="qtd_atividade_total")
	public Long qtdAtividadeTotal;

	/** The qtd atividade finalizada. */
	@Column(name="qtd_atividade_finalizada")
	public Long qtdAtividadeFinalizada;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Getters and Setters
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/* (non-Javadoc)
	 * @see play.db.jpa.Model#getId()
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nome projeto.
	 *
	 * @return the nome projeto
	 */
	public String getNomeProjeto() {
		return this.nomeProjeto;
	}
	
	/**
	 * Sets the nome projeto.
	 *
	 * @param nomeProjeto the new nome projeto
	 */
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	/**
	 * Gets the id gerente.
	 *
	 * @return the id gerente
	 */
	public Long getIdGerente() {
		return this.idGerente;
	}

	/**
	 * Sets the id gerente.
	 *
	 * @param idGerente the new id gerente
	 */
	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}
	
	/**
	 * Gets the id scrum master.
	 *
	 * @return the id scrum master
	 */
	public Long getIdScrumMaster() {
		return this.idScrumMaster;
	}

	/**
	 * Sets the id scrum master.
	 *
	 * @param idScrumMaster the new id scrum master
	 */
	public void setIdScrumMaster(Long idScrumMaster) {
		this.idScrumMaster = idScrumMaster;
	}

	/**
	 * Gets the qtd atividade total.
	 *
	 * @return the qtd atividade total
	 */
	public Long getQtdAtividadeTotal() {
		return this.qtdAtividadeTotal;
	}
	
	/**
	 * Sets the qtd atividade total.
	 *
	 * @param qtdAtividadeTotal the new qtd atividade total
	 */
	public void setQtdAtividadeTotal(Long qtdAtividadeTotal) {
		this.qtdAtividadeTotal = qtdAtividadeTotal;
	}

	/**
	 * Gets the qtd atividade finalizada.
	 *
	 * @return the qtd atividade finalizada
	 */
	public Long getQtdAtividadeFinalizada() {
		return this.qtdAtividadeFinalizada;
	}
	
	/**
	 * Sets the qtd atividade finalizada.
	 *
	 * @param qtdAtividadeFinalizada the new qtd atividade finalizada
	 */
	public void setQtdAtividadeFinalizada(Long qtdAtividadeFinalizada) {
		this.qtdAtividadeFinalizada = qtdAtividadeFinalizada;
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera por id gerente.
	 *
	 * @param idGerente the id gerente
	 * @return the list
	 */
	public static List<VwProjetoAtividadeQuantidade> recuperaPorIdGerente(Long idGerente){
		return find("byIdGerente", idGerente).fetch();
	}
	
	/**
	 * Recupera por id scrum master.
	 *
	 * @param idScrumMaster the id scrum master
	 * @return the list
	 */
	public static List<VwProjetoAtividadeQuantidade> recuperaPorIdScrumMaster(Long idScrumMaster){
		return find("byIdScrumMaster", idScrumMaster).fetch();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Metodos Sobreescritos
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VwProjetoAtividadeQuantidade other = (VwProjetoAtividadeQuantidade) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}