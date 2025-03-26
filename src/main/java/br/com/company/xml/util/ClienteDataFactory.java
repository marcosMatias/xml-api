package br.com.company.xml.util;

import java.util.List;

import br.com.company.xml.dto.ClienteDto;

public class ClienteDataFactory {

	public static List<ClienteDto> criarClientes() {
		return List.of(new ClienteDto("Joao Pereira", "123.456.789-00", 7500.0),
				       new ClienteDto("Ana Souza", "987.654.321-00", 6500.0),
				       new ClienteDto("Carlos Silva", "111.222.333-44", 8500.0));
	}
	
	public static ClienteDto criarCliente() {
		
		return new ClienteDto("Ana Souza", "987.654.321-00", 6500.0);
	}
}
