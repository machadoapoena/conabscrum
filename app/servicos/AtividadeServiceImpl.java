package servicos;

import java.util.List;

import models.Atividade;
import dominio.TipoSituacaoAtividadeEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * AtividadeServiceImpl.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public class AtividadeServiceImpl implements AtividadeService {

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesEmProgresso()
	 */
	@Override
	public List<Atividade> recuperaAtividadesEmProgresso() {
		return Atividade.recuperaPorSituacao(TipoSituacaoAtividadeEnum.EM_PROGRESSO);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesDoUsuario(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesDoUsuario(String usuario) {
		return Atividade.recuperaPorUsuario(usuario);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaQtdAtividadesFinalizadasDoUsuario(java.lang.String)
	 */
	@Override
	public Long recuperaQtdAtividadesFinalizadasDoUsuario(String login) {
		return Atividade.recuperaQuantidadeAtividadesPorUsuarioSituacao(login, TipoSituacaoAtividadeEnum.FINALIZADA);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesDeProjetosPorGerente(java.lang.Long)
	 */
	@Override
	public List<Atividade> recuperaAtividadesDeProjetosPorGerente(String login) {
		return Atividade.recuperaAtividadesPorLoginGerente(login);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesDeProjetosPorScrumMaster(java.lang.Long)
	 */
	@Override
	public List<Atividade> recuperaAtividadesDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesPorLoginScrumMaster(login);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaTodasAtividadesSemUsuario()
	 */
	@Override
	public List<Atividade> recuperaTodasAtividadesSemUsuario() {
		return Atividade.recuperaAtividadesSemUsuario();
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesSemUsuarioDeProjetosPorGerente(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesSemUsuarioDeProjetosPorGerente(String login) {
		return Atividade.recuperaAtividadesSemUsuarioPorLoginGerente(login);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesSemUsuarioDeProjetosPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesSemUsuarioDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesSemUsuarioPorLoginScrumMaster(login);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesAFazerDeProjetosPorGerente(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesAFazerDeProjetosPorGerente(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginGerente(login, TipoSituacaoAtividadeEnum.A_FAZER);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesEmProgressoDeProjetosPorGerente(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesEmProgressoDeProjetosPorGerente(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginGerente(login, TipoSituacaoAtividadeEnum.EM_PROGRESSO);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesImpedidoDeProjetosPorGerente(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesImpedidoDeProjetosPorGerente(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginGerente(login, TipoSituacaoAtividadeEnum.IMPEDIDO);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesFinalizadaDeProjetosPorGerente(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesFinalizadaDeProjetosPorGerente(String login) {
		return  Atividade.recuperaAtividadesPorSituacaoLoginGerente(login, TipoSituacaoAtividadeEnum.FINALIZADA);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesAFazerDeProjetosPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesAFazerDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginScrumMaster(login, TipoSituacaoAtividadeEnum.A_FAZER);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesEmProgressoDeProjetosPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesEmProgressoDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginScrumMaster(login, TipoSituacaoAtividadeEnum.EM_PROGRESSO);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesImpedidoDeProjetosPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesImpedidoDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginScrumMaster(login, TipoSituacaoAtividadeEnum.IMPEDIDO);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesFinalizadaDeProjetosPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Atividade> recuperaAtividadesFinalizadaDeProjetosPorScrumMaster(String login) {
		return Atividade.recuperaAtividadesPorSituacaoLoginScrumMaster(login, TipoSituacaoAtividadeEnum.FINALIZADA);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#recuperaAtividadesDeIteracao(java.lang.Long)
	 */
	@Override
	public List<Atividade> recuperaAtividadesDeIteracao(Long idIteracao) {
		return Atividade.recuperaAtividadesPorIteracao(idIteracao);
	}

	/* (non-Javadoc)
	 * @see servicos.AtividadeService#atualizaSituacao(java.lang.Long, dominio.TipoSituacaoAtividadeEnum)
	 */
	@Override
	public void atualizaSituacao(Long idAtividade, TipoSituacaoAtividadeEnum situacao) {
		Atividade atividade = Atividade.findById(idAtividade);
		atividade.situacao = situacao;
		atividade.save();
	}
}