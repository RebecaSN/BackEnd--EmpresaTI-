package SoulCode.Services.Models;

public enum StatusServico {
	
	RECEBIDO("Recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido"),
	ARQUIVADO("Arquivado");
	
	private String descricao;
	
	//Construtor do ENUM
	private StatusServico(String descricao) {
		this.descricao = descricao;
	}
	
	//Get do atributo Descricao
	public String getDescricao() {
		return descricao;
	}

	
	
	

}
