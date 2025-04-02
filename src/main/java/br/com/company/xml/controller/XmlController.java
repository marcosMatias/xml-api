package br.com.company.xml.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.company.xml.dto.ResponseDto;
import br.com.company.xml.service.XmlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/xml")
@Tag(name = "Processa XML")
public class XmlController {

	private final XmlService xmlService;

	public XmlController(XmlService xmlService) {
		this.xmlService = xmlService;
	}

	
	@PostMapping
	@Operation(summary = "Modifica o XML",
			description = "Este endpoint realiza modificações nas tags de um XML.")	
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "XML processado com SUCESSO",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
            @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})
            })	
	public ResponseDto processarXml() {

		return xmlService.processar();

	}

}
