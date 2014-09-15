package dominio;


public enum TipoPerfilEnum {
	
	GESTOR("Gestor",1), GERENTE("Gerente",2), SCRUMMASTER("Scrum Master",3), DESENVOLVEDOR("Desenvolvedor",4);
	
	private final int valor;
	private String label;
	
	TipoPerfilEnum(String texto, int opcao){
		valor = opcao;
		label = texto;
	}
	TipoPerfilEnum(int opcao){
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
