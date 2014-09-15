package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import anotacoes.JsonExclude;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import dominio.TipoMensagemEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Mensagem.java
 *
 * @date 22/08/2014
 * @author apoena.machado
 */
@Entity
@Table(name="tb_mensagem")
public class Mensagem extends Model {

	@PostLoad
	public void carregarAtributosTransient(){
	    this.dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.data);
	}
	
	/** The mensagem. */
	@Required
	@MaxSize(255)
	@Column(name="mensagem")
	public String mensagem;
	
	/** The tipo mensagem. */
	@Required
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_mensagem")
	public TipoMensagemEnum tipoMensagem;
	
	/** The data cadastro. */
	@Required
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="data")
	public Date data;
	
    /** The lst mensagem usuario. */
	@JsonExclude
    @OrderBy("mensagem")
    @OneToMany(mappedBy = "mensagem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensagemUsuario> lstMensagemUsuario;
    
	/** The usuario. */
	@JsonExclude
	@ManyToOne
	@JoinColumn(name = "usuario_fk")
	public Usuario usuario;
	
	@Transient
	@Expose
	public String dataFormatada;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera todas mensagens do usuario.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<Mensagem> recuperaTodasMensagensDoUsuario(String login){
		return find("select msg, lista.lida from Mensagem msg JOIN msg.lstMensagemUsuario lista where lista.usuario.login = ?1 ORDER BY msg.id desc", login).fetch();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Metodos sobreescritos
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/* (non-Javadoc)
	 * @see play.db.jpa.JPABase#toString()
	 */
	@Override
	public String toString() {
		return this.mensagem;
	}
}