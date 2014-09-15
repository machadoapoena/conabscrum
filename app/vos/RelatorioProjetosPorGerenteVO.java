package vos;


/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * RelatorioProjetosPorGerenteVO.java
 *
 * @date 15/08/2014
 * @author apoena.machado
 */
public class RelatorioProjetosPorGerenteVO {
	
	/** The nome. */
	private String nome;
	
	/** The gerente. */
	private String gerente;
	
	/** The scrum master. */
	private String scrumMaster;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Getters and Setters
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
	
		return this.nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
	
		this.nome = nome;
	}
	
	/**
	 * Gets the gerente.
	 *
	 * @return the gerente
	 */
	public String getGerente() {
	
		return this.gerente;
	}
	
	/**
	 * Sets the gerente.
	 *
	 * @param gerente the new gerente
	 */
	public void setGerente(String gerente) {
	
		this.gerente = gerente;
	}

	/**
	 * Gets the scrum master.
	 *
	 * @return the scrum master
	 */
	public String getScrumMaster() {
	
		return this.scrumMaster;
	}

	/**
	 * Sets the scrum master.
	 *
	 * @param scrumMaster the new scrum master
	 */
	public void setScrumMaster(String scrumMaster) {
	
		this.scrumMaster = scrumMaster;
	}
}