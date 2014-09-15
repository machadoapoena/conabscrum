package servicos;

import java.util.List;

import models.MensagemUsuario;
import models.Usuario;
import dominio.MensagemEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemService.java
 *
 * @date 25/08/2014
 * @author apoena.machado
 */
public interface MensagemService {

	/**
	 * Salvar mensagem.
	 *
	 * @param msg the msg
	 * @param usuario the usuario
	 * @param args the args
	 */
	public void salvarMensagem(MensagemEnum msg, Usuario usuario, String... args);
	
	/**
	 * Recupera todas mensagens para usuario.
	 *
	 * @param login the login
	 * @return the list
	 */
	public List<MensagemUsuario> recuperaTodasMensagensParaUsuario(String login);
}
