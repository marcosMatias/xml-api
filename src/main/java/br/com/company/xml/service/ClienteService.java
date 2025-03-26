package br.com.company.xml.service;

import org.springframework.stereotype.Service;
import br.com.company.xml.dto.ClienteDto;
import br.com.company.xml.dto.ClientesDto;
import br.com.company.xml.util.ClienteDataFactory;

@Service
public class ClienteService {

	public ClientesDto listarClientes() {

		return new ClientesDto(ClienteDataFactory.criarClientes());

	}

	public ClienteDto obterCliente() {

		return ClienteDataFactory.criarCliente();

	}
}
