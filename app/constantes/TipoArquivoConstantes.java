package constantes;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * TipoArquivoConstantes.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public enum TipoArquivoConstantes {
	PDF, XLSX, XLS, GIF, PNG, JASPER;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// * @see java.lang.Enum#toString()
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}