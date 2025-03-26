package br.com.company.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "cliente")
public class ClienteDto {

	@JacksonXmlProperty(localName = "nome")
	private String nome;

	@JacksonXmlProperty(localName = "cpf")
	private String cpf;

	@JacksonXmlProperty(localName = "renda")
	private Double renda;
}
