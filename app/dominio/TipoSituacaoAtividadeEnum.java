package dominio;


public enum TipoSituacaoAtividadeEnum {
	
	A_FAZER("A Fazer",1), IMPEDIDO("Impedido", 2), EM_PROGRESSO("Em progresso",3), FINALIZADA("Finalizada",4);
	
	private final int valor;
	private String label;
	
	TipoSituacaoAtividadeEnum(String texto, int opcao){
		valor = opcao;
		label = texto;
	}
	TipoSituacaoAtividadeEnum(int opcao){
		valor = opcao;
	}
	public int getValor(){
		return valor;
	}
	public String getLabel(){
		return label;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
}
