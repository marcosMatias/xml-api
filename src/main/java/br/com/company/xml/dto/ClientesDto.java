package br.com.company.xml.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "clientes")
public class ClientesDto {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "cliente")
	private List<ClienteDto> clientes;

}