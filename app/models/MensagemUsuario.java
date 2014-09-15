package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
import anotacoes.JsonExclude;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemUsuario.java
 *
 * @date 22/08/2014
 * @author apoena.machado
 */
@Entity
@Table(name="rl_mensagem_usuario")
public class MensagemUsuario extends GenericModel {

	
    /** The id. */
    @EmbeddedId
    private CompositeId id;
    
	/** The lida. */
	@Required
	@Column(name="lida")
	public boolean lida;
	
    /** The usuario. */
    @JsonExclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_FK", insertable = false, updatable = false)
    private Usuario usuario;

    /** The mensagem. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENSAGEM_FK", insertable = false, updatable = false)
    private Mensagem mensagem;
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Get/Set.
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public CompositeId getId() {
        return this.id;
    }
    public void setId(final CompositeId id) {
        this.id = id;
    }
    
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Acesso a dados
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Recupera todas mensagens do usuario.
	 *
	 * @param login the login
	 * @return the list
	 */
	public static List<MensagemUsuario> recuperaTodasMensagensDoUsuario(String login){
		return find("usuario.login = ?1 ORDER BY id desc", login).fetch(5);
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// * @see java.lang.Object#hashCode()
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// * @see java.lang.Object#equals(java.lang.Object)
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof MensagemUsuario)) {
			return false;
		}
		final MensagemUsuario other = (MensagemUsuario) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}    
    
    @Embeddable
    public static class CompositeId implements Serializable {

        /** The id usuario. */
        @Column(name = "USUARIO_FK", insertable = false, updatable = false)
        private Long idUsuario;
        
        /** The mensagem id. */
        @Column(name = "MENSAGEM_FK", insertable = false, updatable = false)
        private Long mensagemId;

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Constructors.
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        public CompositeId(final Long idUsuario, final Long mensagemId) {
            super();
            this.idUsuario = idUsuario;
            this.mensagemId = mensagemId;
        }
        
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Get/Set.
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		/**
         * Gets the id usuario.
         *
         * @return the id usuario
         */
        public Long getIdUsuario() {
			return this.idUsuario;
		}
		
		/**
		 * Sets the id usuario.
		 *
		 * @param idUsuario the new id usuario
		 */
		public void setIdUsuario(Long idUsuario) {
			this.idUsuario = idUsuario;
		}

		/**
		 * Gets the mensagem id.
		 *
		 * @return the mensagem id
		 */
		public Long getMensagemId() {
		
			return this.mensagemId;
		}

		/**
		 * Sets the mensagem id.
		 *
		 * @param mensagemId the new mensagem id
		 */
		public void setMensagemId(Long mensagemId) {
		
			this.mensagemId = mensagemId;
		}
        
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // * @see java.lang.Object#hashCode()
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (this.mensagemId == null ? 0 : this.mensagemId.hashCode());
            result = prime * result + (this.idUsuario == null ? 0 : this.idUsuario.hashCode());
            return result;
        }

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // * @see java.lang.Object#equals(java.lang.Object)
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof CompositeId)) {
                return false;
            }
            final CompositeId other = (CompositeId) obj;
            if (this.mensagemId == null) {
                if (other.mensagemId != null) {
                    return false;
                }
            } else if (!this.mensagemId.equals(other.mensagemId)) {
                return false;
            }
            if (this.idUsuario == null) {
                if (other.idUsuario != null) {
                    return false;
                }
            } else if (!this.idUsuario.equals(other.idUsuario)) {
                return false;
            }
            return true;
        }
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // * @see java.lang.Object#toString()
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        @Override
        public String toString() {
            return "CompositeId [mensagemId=" + this.mensagemId + ", idUsuario=" + this.idUsuario + "]";
        }
    }
}