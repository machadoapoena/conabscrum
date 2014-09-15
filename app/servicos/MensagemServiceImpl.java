package servicos;

import java.util.List;

import jobs.MensagemSaveBatchJob;
import models.Mensagem;
import models.MensagemUsuario;
import models.Usuario;
import utils.DateUtil;
import utils.StringUtil;
import dominio.MensagemEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemServiceImpl.java
 *
 * @date 25/08/2014
 * @author apoena.machado
 */
public class MensagemServiceImpl implements MensagemService {

	/* (non-Javadoc)
	 * @see servicos.MensagemService#salvarMensagem(dominio.MensagemEnum, models.Usuario, java.lang.Object[])
	 */
	@Override
	public void salvarMensagem(MensagemEnum msg, Usuario usuario, String... args) {

		Mensagem mensagem = new Mensagem();
		mensagem.mensagem = msg.getLabel();
		mensagem.tipoMensagem = msg.getTipoMensagem();
		mensagem.usuario = usuario;
		mensagem.data = DateUtil.timestampAtual();
		if (args.length > 0) {
			if (msg == MensagemEnum.NOVO_PROJETO){
				mensagem.mensagem = StringUtil.replaceEach(mensagem.mensagem, new String[]{"$1", "$2"}, (String[]) args);
			}
        }
		mensagem.save();
		mensagem.em().flush();
		mensagem.em().getTransaction().commit();
		new MensagemSaveBatchJob(mensagem).now();
	}

	/* (non-Javadoc)
	 * @see servicos.MensagemService#recuperaTodasMensagensParaUsuario(java.lang.String)
	 */
	@Override
	public List<MensagemUsuario> recuperaTodasMensagensParaUsuario(String login) {
		//return Mensagem.recuperaTodasMensagensDoUsuario(login);
		return MensagemUsuario.recuperaTodasMensagensDoUsuario(login);
	}
}