package servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Projeto;
import models.Usuario;
import models.VwProjetoAtividadeQuantidade;
import utils.StringUtil;
import vos.RelatorioProjetosPorGerenteVO;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * RelatorioServiceImpl.java
 *
 * @date 15/08/2014
 * @author apoena.machado
 */
public class RelatorioServiceImpl implements RelatorioService {

	/* (non-Javadoc)
	 * @see servicos.RelatorioService#recuperarDadosProjetosPorGerente(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RelatorioProjetosPorGerenteVO> recuperarDadosProjetosPorGerente(String login) {

		RelatorioProjetosPorGerenteVO dados;
		List<RelatorioProjetosPorGerenteVO> lstDadosRelatorio = new ArrayList<RelatorioProjetosPorGerenteVO>();
		List<Projeto> lstProjeto = null;
		if (StringUtil.isEmpty(login)) {
			lstProjeto = Projeto.findAll();
		} else {
			lstProjeto = Projeto.recuperaProjetosPorGerente(login);
		}
		for (Iterator iterator = lstProjeto.iterator(); iterator.hasNext();) {
			dados = new RelatorioProjetosPorGerenteVO();
			Projeto projeto = (Projeto) iterator.next();
			dados.setNome(projeto.nome);
			dados.setGerente(projeto.gerente.nome);
			dados.setScrumMaster(projeto.scrumMaster.nome);
			lstDadosRelatorio.add(dados);
		}
		return lstDadosRelatorio;
	}

	/* (non-Javadoc)
	 * @see servicos.RelatorioService#recuperarDadosProjetosAtividadeQuantidade(java.lang.String)
	 */
	@Override
	public List<VwProjetoAtividadeQuantidade> recuperarDadosProjetosAtividadeQuantidade(String login) {

		List<VwProjetoAtividadeQuantidade> lstDadosRelatorio = new ArrayList<VwProjetoAtividadeQuantidade>();
		if (!StringUtil.isEmpty(login)){
			Usuario usuario = Usuario.recuperaUsuariosPorLogin(login);	
			lstDadosRelatorio = VwProjetoAtividadeQuantidade.recuperaPorIdGerente(usuario.id);
		} else {
			lstDadosRelatorio = VwProjetoAtividadeQuantidade.findAll();
		}
		return lstDadosRelatorio;
	}
}