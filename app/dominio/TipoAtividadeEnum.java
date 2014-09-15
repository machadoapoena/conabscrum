package dominio;


public enum TipoAtividadeEnum {
	
	BUG("Bug",1), NOVA_FUNCIONALIDADE("Nova Funcionalidade", 2), ALTERACAO_FUNCIONALIDAE("Alteração de Funcionalidade",3);
	
	private final int valor;
	private String label;
	
	TipoAtividadeEnum(String texto, int opcao){
		valor = opcao;
		label = texto;
	}
	TipoAtividadeEnum(int opcao){
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
