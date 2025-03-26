package br.com.company.xml.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.company.xml.dto.ClienteDto;
import br.com.company.xml.dto.ClientesDto;
import br.com.company.xml.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/xml")
@Tag(name="Clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value = "/clientes", produces = MediaType.APPLICATION_XML_VALUE)
	@Operation(summary = "Retorna Lista de Clientes",
			description = "Este endpoint retorna uma lista de clientes fixa com nome, CPF e renda no formato XML.")	
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso",content = {@Content (mediaType = "application/xml",schema = @Schema(implementation = ClientesDto.class))})})	
	public ClientesDto gerarClientesXml() {

		return clienteService.listarClientes();

	}
	
	@GetMapping(value = "/cliente", produces = MediaType.APPLICATION_XML_VALUE)
	@Operation(summary = "Retorna um  Cliente",
			   description = "Este endpoint retorna um cliente fixo com nome, CPF e renda no formato XML.")	
	@ApiResponses(value = {
                 @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso",content = {@Content (mediaType = "application/xml",schema = @Schema(implementation = ClienteDto.class))})})			   	        	
	public ClienteDto gerarClienteXml() {

		return clienteService.obterCliente();

	}

}
