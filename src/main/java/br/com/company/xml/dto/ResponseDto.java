package br.com.company.xml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	
	private int statusCode;	
	private String data;
	private String message;
	private String description;

}
