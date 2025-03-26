package br.com.company.xml.config.springdoc;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringDocConfigurations {
	
	

    @Bean
    OpenAPI openApi() {
		return new OpenAPI().components(new Components())
														.info(new Info().title("API XML - Geração de Clientes em XML")
								                				        .description("Esta é uma API REST desenvolvida com Spring Boot 3.4.3 e JDK 17 que gera e retorna dados no formato XML. "
														                             +" Ela simula uma lista de clientes e permite o consumo via endpoints RESTful com suporte à serialização automática em XML.")																

								                				        .version("1.0.1")								                				        								                				        
														.license(new License()
									                            .name("Copyright © 2025 Tecnologia da Informação")
									                            ))
														.servers(retornarServidores())
														;
		
	}


    private List<Server> retornarServidores() {
	    return Collections.singletonList(new Server()
	                      .description("Servidor de Desenvolvimento")
	                      .url("http://localhost:8080/"));
	}

}
