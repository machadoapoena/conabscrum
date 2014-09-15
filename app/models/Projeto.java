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
import utils.StringUtil;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Projeto.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@Entity
@Table(name="tb_projeto")
public class Projeto extends Model implements Comparable<Projeto> {

	/** The nome. */
	@Required
	@Unique
	@MinSize(2)
	@MaxSize(100)
	@Column(name="nome")
	public String nome;
	
	/** The descricao. */
	@Required
	@MinSize(3)
	@MaxSize(255)
	@Column(name="descricao")
	public String descricao;
	
	/** The ativo. */
	@Required
	@Column(name="ativo")
	public boolean ativo;
	
	/** The gerente. */
	@ManyToOne
	@Required
	@JoinColumn(name = "gerente_fk")
	public Usuario gerente;	
	
	/** The scrum master. */
	@ManyToOne
	@Required
	@JoinColumn(name = "scrum_master_fk")
	public Usuario scrumMaster;
	
	/** The iteracoes. */
	@OneToMany(mappedBy="projeto",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Iteracao> iteracoes;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera projetos por gerente.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	public static List<Projeto> recuperaProjetosPorGerente(String login){
		return find("gerente.login = ?1 order by gerente.nome", login).fetch();
	}
	
	/**
	 * Recupera projetos por scrum master.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	public static List<Projeto> recuperaProjetosPorScrumMaster(String login){
		return find("scrumMaster.login = ?1 order by scrumMaster.nome", login).fetch();
	}
	
	/**
	 * Recupera projetos por desenvolvedor.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	public static List<Projeto> recuperaProjetosPorDesenvolvedor(Long idUsuario){
		return find("select distinct proj from Projeto proj JOIN proj.iteracoes ite JOIN ite.backLogs back JOIN back.atividades ativ WHERE ativ.usuario.id = ?1 order by proj.nome", idUsuario).fetch();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Metodos sobreescritos
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#toString()
	 */
	@Override
	public String toString() {
		return this.nome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Projeto o) {
		return StringUtil.compare(this.nome, o.nome);
	}
}