package excecoes;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * JasperFileNotFoundException.java
 *
 * @date 15/08/2014
 * @author apoena.machado
 */
public class JasperFileNotFoundException extends Exception {

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Jasper file does not exist!";
	}
}
