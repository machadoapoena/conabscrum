package excecoes;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * ArquivoJasperNaoEncontradoExcecao.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public class ArquivoJasperNaoEncontradoExcecao extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4395406840804967088L;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// * @see java.lang.Throwable#getMessage()
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public String getMessage() {
		return "Arquivo Jasper não encontrado!";
	}
}