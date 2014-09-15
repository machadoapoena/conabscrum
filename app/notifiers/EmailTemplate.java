package notifiers;

import models.Atividade;
import models.Projeto;
import models.Usuario;

import org.apache.commons.mail.EmailException;

import play.mvc.Mailer;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * EmailTemplate.java
 *
 * @date 14/08/2014
 * @author apoena.machado
 */
public class EmailTemplate extends Mailer{
	
 	/** The Constant EMAIL_ASSUNTO_NOVO_PROJETO. */
	 private static final String EMAIL_ASSUNTO_NOVO_PROJETO = "CONABSCRUM - Novo projeto para você";
	
	/** The Constant EMAIL_ASSUNTO_NOVA_ATIVIDADE. */
	private static final String EMAIL_ASSUNTO_NOVA_ATIVIDADE = "CONABSCRUM - Nova atividade para você";

	/**
	  * Nova atividade atribuida.
	  *
	  * @param usuario the usuario
	  * @param atividade the atividade
	  * @param loginRemetente the login remetente
 	 * @throws EmailException 
	  */
	 public static void novaAtividadeAtribuida(Usuario usuario, Atividade atividade, String loginRemetente) throws EmailException {
 		
	      setSubject(EMAIL_ASSUNTO_NOVA_ATIVIDADE);
	      addRecipient(usuario.login);
	      setFrom("ConabScrum <"+ loginRemetente + ">");
	      send(atividade);
	   }
	 
	 /**
 	 * Novo projeto atribuido.
 	 *
 	 * @param projeto the projeto
 	 * @throws EmailException the email exception
 	 */
 	public static void novoProjetoAtribuido(Projeto projeto) throws EmailException {
	 		
	      setSubject(EMAIL_ASSUNTO_NOVO_PROJETO);
	      addRecipient(projeto.scrumMaster.login);
	      setFrom("ConabScrum <"+ projeto.gerente.login + ">");
	      send(projeto);
	   }
}