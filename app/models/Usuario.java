package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import anotacoes.JsonExclude;

import com.google.gson.annotations.Expose;

import controllers.CRUD.Order;
import dominio.TipoPerfilEnum;
import dominio.TipoSituacaoAtividadeEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Usuario.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
@Entity
@Table(name="tb_usuario")
public class Usuario extends Model implements Comparable<Usuario> {

	/** The nome. */
	@Order(order="ASC")
	@Required
	@Unique
	@MinSize(3)
	@MaxSize(100)
	@Column(name="nome")
	public String nome;
	
	/** The login. */
	@Required
	@Email
	@Unique
	@MinSize(3)
	@MaxSize(30)
	@Column(name="login")
	public String login;
	
	/** The senha. */
	@Required
	@Password
	@MinSize(6)
	@MaxSize(8)
	@Column(name="senha")
	public String senha;
	
	@Required
	@Enumerated(EnumType.ORDINAL)
	@Column(name="perfil")
	public TipoPerfilEnum perfil;	
	
	/** The avatar image. */
	@Column(name="avatar")
	public byte[] avatar;	
	
	/** The projetos gerente. */
	@JsonExclude
	@OneToMany(mappedBy="gerente",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Projeto> projetosGerente;
	
	/** The projetos scrum master. */
	@JsonExclude
	@OneToMany(mappedBy="scrumMaster",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Projeto> projetosScrumMaster;
	
	/** The atividades. */
	@JsonExclude
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Atividade> atividades;
	
    /** The lst mensagem usuario. */
    @OrderBy("mensagem")
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensagemUsuario> lstMensagemUsuario;
    
	/** The lst mensagens. */
	@JsonExclude
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Mensagem> lstMensagens;
	
	/** The qtd atividade finalizadas. */
	@Expose
	@Transient
	public Long qtdAtividadeFinalizadas;
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Getters e Setters
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Long getQtdAtividadeFinalizadas() {
		return this.qtdAtividadeFinalizadas;
	}
	public void setQtdAtividadeFinalizadas(Long qtdAtividadeFinalizadas) {
		this.qtdAtividadeFinalizadas = qtdAtividadeFinalizadas;
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera usuarios sem atividade.
	 *
	 * @return the list
	 */
	public static List<Usuario> recuperaUsuariosSemAtividade(){
		return find("select usu from Usuario usu left join usu.atividades atividade where atividade.id = null  or (atividade.situacao != ?1 AND atividade.situacao != ?2) order by usu.nome", TipoSituacaoAtividadeEnum.A_FAZER, TipoSituacaoAtividadeEnum.EM_PROGRESSO).fetch();
	}
	
	/**
	 * Recupera usuarios por login.
	 *
	 * @param login the login
	 * @return the usuario
	 */
	public static Usuario recuperaUsuariosPorLogin(String login){
		return find("byLogin", login).first();
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Usuario o) {
		int last = this.nome.compareTo(o.nome);
        return last == 0 ? this.nome.compareTo(o.nome) : last;
	}
}
