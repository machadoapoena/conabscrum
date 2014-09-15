package dominio;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemEnum.java
 *
 * @date 25/08/2014
 * @author apoena.machado
 */
public enum MensagemEnum {
	
	NOVO_PROJETO("Novo projeto em andamento '$1' criado por  $2", TipoMensagemEnum.PUBLICA), 
	NOVA_ATIVIDADE_VINCULADA("Nova atividade '$1' atribuída a $2", TipoMensagemEnum.PUBLICA),
	NOVA_ATIVIDADE_SEM_VINCULO("Nova atividade '$1' sem vínculo", TipoMensagemEnum.PUBLICA);
	
	/** The label. */
	private String label;
	
	/** The tipo mensagem. */
	private TipoMensagemEnum tipoMensagem;
	
	/**
	 * Instantiates a new tipo mensagem enum.
	 *
	 * @param texto the texto
	 */
	MensagemEnum(String texto, TipoMensagemEnum tipoMensagem){
		this.label = texto;
		this.tipoMensagem = tipoMensagem;
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel(){
		return label;
	}
	
	/**
	 * Gets the tipo mensagem.
	 *
	 * @return the tipo mensagem
	 */
	public TipoMensagemEnum getTipoMensagem(){
		return tipoMensagem;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.label;
	}
}