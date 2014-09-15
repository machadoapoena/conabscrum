package servicos;

import java.util.List;

import models.Iteracao;
import models.Usuario;
import utils.DateUtil;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * IteracaoServiceImpl.java
 *
 * @date 05/08/2014
 * @author apoena.machado
 */
public class IteracaoServiceImpl implements IteracaoService {
	
	/* (non-Javadoc)
	 * @see servicos.IteracaoService#recuperaIteracoesVencendo(int)
	 */
	@Override
	public List<Iteracao> recuperaIteracoesVencendo(int qtdDias) {
		List<Iteracao> lstIteracao = Iteracao.recuperaIteracaoPorDataFinalEntreDatas(DateUtil.dataAtual(), DateUtil.adicionaDias(DateUtil.dataAtual(), qtdDias));
		Long quantidadeDeDiasEntreDatas;
		for (Iteracao iteracao : lstIteracao) {
			quantidadeDeDiasEntreDatas = DateUtil.quantidadeDeDiasEntreDatas(DateUtil.dataAtual(), iteracao.dataFinal);
			iteracao.setQtdDiasRestantesFim(quantidadeDeDiasEntreDatas.intValue());
		}
		return lstIteracao;
	}

	/* (non-Javadoc)
	 * @see servicos.IteracaoService#recuperaIteracoesDoProjeto(java.lang.Long)
	 */
	@Override
	public List<Iteracao> recuperaIteracoesDoProjeto(Long idProjeto) {
		return Iteracao.recuperaIteracaoPorProjeto(idProjeto);
	}
}