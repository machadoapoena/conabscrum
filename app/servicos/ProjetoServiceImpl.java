package servicos;

import java.util.List;

import models.Projeto;
import models.Usuario;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * ProjetoServiceImpl.java
 *
 * @date 01/08/2014
 * @author apoena.machado
 */
public class ProjetoServiceImpl implements ProjetoService {

	/* (non-Javadoc)
	 * @see servicos.ProjetoService#recuperarProjetos()
	 */
	@Override
	public List<Projeto> recuperarProjetos() {
		return Projeto.findAll();
	}

	/* (non-Javadoc)
	 * @see servicos.ProjetoService#recuperarProjetoPorId(java.lang.Long)
	 */
	@Override
	public List<Projeto> recuperarProjetoPorId(Long id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see servicos.ProjetoService#recuperarProjetoPorGerente(java.lang.String)
	 */
	@Override
	public List<Projeto> recuperarProjetoPorGerente(String login) {
		return Projeto.recuperaProjetosPorGerente(login);
	}

	/* (non-Javadoc)
	 * @see servicos.ProjetoService#recuperarProjetoPorScrumMaster(java.lang.String)
	 */
	@Override
	public List<Projeto> recuperarProjetoPorScrumMaster(String login) {
		return Projeto.recuperaProjetosPorScrumMaster(login);
	}

	/* (non-Javadoc)
	 * @see servicos.ProjetoService#recuperarProjetoPorDesenvolvedor(java.lang.String)
	 */
	@Override
	public List<Projeto> recuperarProjetoPorDesenvolvedor(String login) {
		Usuario usuario = Usuario.recuperaUsuariosPorLogin(login);
		return Projeto.recuperaProjetosPorDesenvolvedor(usuario.id);
	}
}
