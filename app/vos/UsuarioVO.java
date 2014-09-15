package vos;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * UsuarioVO.java
 *
 * @date 01/08/2014
 * @author apoena.machado
 */
public class UsuarioVO {
	
	/** The nome. */
	private String nome;
	
	/** The qtd ativades concluidas. */
	private Long qtdAtivadesConcluidas;
	
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
	 * Gets the qtd ativades concluidas.
	 *
	 * @return the qtd ativades concluidas
	 */
	public Long getQtdAtivadesConcluidas() {
		return this.qtdAtivadesConcluidas;
	}
	/**
	 * Sets the qtd ativades concluidas.
	 *
	 * @param qtdAtivadesConcluidas the new qtd ativades concluidas
	 */
	public void setQtdAtivadesConcluidas(Long qtdAtivadesConcluidas) {
		this.qtdAtivadesConcluidas = qtdAtivadesConcluidas;
	}
}