package servicos;

import java.util.List;

import models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * UsuarioServiceImpl.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public class UsuarioServiceImpl implements UsuarioService {
	
	/** The atividade service. */
	public static AtividadeService atividadeService;
	
	/**
	 * Sets the atividade service.
	 *
	 * @param atividadeService the new atividade service
	 */
	@Autowired
	public void setAtividadeService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
	}

	/* (non-Javadoc)
	 * @see servicos.UsuarioService#recuperaUsuariosSemAtividade()
	 */
	@Override
	public List<Usuario> recuperaUsuariosSemAtividade() {
		
		List<Usuario> lstUsuarios = Usuario.recuperaUsuariosSemAtividade();
		return lstUsuarios;
	}
	
	/* (non-Javadoc)
	 * @see servicos.UsuarioService#recuperaUsuariosSemAtividadeComQtdFinalizadas()
	 */
	@Override
	public List<Usuario> recuperaUsuariosSemAtividadeComQtdFinalizadas() {

		List<Usuario> lstUsuarios = this.recuperaUsuariosSemAtividade();
		for (Usuario usuario : lstUsuarios) {
			this.getQtdAtividadeFinalizadasUsuario(usuario);
		}
		return lstUsuarios;
	}
	
	/* (non-Javadoc)
	 * @see servicos.UsuarioService#recuperaUsuarioLogado(java.lang.String)
	 */
	@Override
	public Usuario recuperaUsuarioLogado(String login) {

		return Usuario.recuperaUsuariosPorLogin(login);
	}
	
	/**
	 * Gets the qtd atividade finalizadas usuario.
	 *
	 * @param usuario the usuario
	 * @return the qtd atividade finalizadas usuario
	 */
	private static void getQtdAtividadeFinalizadasUsuario(Usuario usuario){
		Long qtdAtividade = atividadeService.recuperaQtdAtividadesFinalizadasDoUsuario(usuario.login);
		usuario.setQtdAtividadeFinalizadas(qtdAtividade);
	}
}