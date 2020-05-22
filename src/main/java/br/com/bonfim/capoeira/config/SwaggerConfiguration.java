package br.com.bonfim.capoeira.config;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())

                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("Deu erro interno no servidor.")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbidden! Você não pode acessar esse recurso.")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("O recurso que você buscou não foi encontrado.")
                                        .build()))

                .ignoredParameterTypes(Aluno.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para facilitar o envio do Authorization Bearer Token")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()));
    }

    private ApiInfo apiInfo() {
        Contact contato = new Contact("Ricardo",
                "", "ricardo.rodrigues.alves@hotmail.com");

        return new ApiInfoBuilder()
                .title("Bonfim Capoeira API Documentation")
                .description("Esta é a documentação interativa da Rest API do projeto Bonfim Capoeira. Tente enviar algum request ;)")
                .version("1.0")
                .contact(contato)
                .build();
    }

}
