package jobs;

import java.util.List;

import models.Mensagem;
import models.MensagemUsuario;
import models.MensagemUsuario.CompositeId;
import models.Usuario;
import play.jobs.Job;
import dominio.TipoMensagemEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemSaveBatchJob.java
 *
 * @date 25/08/2014
 * @author apoena.machado
 */
public class MensagemSaveBatchJob extends Job {

	private Mensagem mensagem;
	
    /**
     * Instantiates a new mensagem save batch job.
     *
     * @param mensagem the mensagem
     */
    public MensagemSaveBatchJob(Mensagem mensagem) {
    	this.mensagem = mensagem;
	}

	/* (non-Javadoc)
	 * @see play.jobs.Job#doJob()
	 */
	public void doJob() {
    	
		if (mensagem.tipoMensagem == TipoMensagemEnum.PUBLICA){
			List<Usuario> lstUsuarios = Usuario.findAll();
			for (Usuario usuario : lstUsuarios) {
				final CompositeId compositeId = new MensagemUsuario.CompositeId(usuario.id, mensagem.id);
				MensagemUsuario mensagemUsuario = new MensagemUsuario();
				mensagemUsuario.setId(compositeId);
				mensagemUsuario.lida = false;
				mensagemUsuario.save();
			}
		}
    }

	/**
	 * Gets the mensagem.
	 *
	 * @return the mensagem
	 */
	public Mensagem getMensagem() {
		return this.mensagem;
	}
	
	/**
	 * Sets the mensagem.
	 *
	 * @param mensagem the new mensagem
	 */
	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
}