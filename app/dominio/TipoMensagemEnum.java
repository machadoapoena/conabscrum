package dominio;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * TipoMensagemEnum.java
 *
 * @date 22/08/2014
 * @author apoena.machado
 */
public enum TipoMensagemEnum {
	
	PUBLICA("Pública"), PRIVADA("Privada");
	
	/** The label. */
	private String label;
	
	/**
	 * Instantiates a new tipo mensagem enum.
	 *
	 * @param texto the texto
	 */
	TipoMensagemEnum(String texto){
		label = texto;
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel(){
		return label;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.label;
	}
}