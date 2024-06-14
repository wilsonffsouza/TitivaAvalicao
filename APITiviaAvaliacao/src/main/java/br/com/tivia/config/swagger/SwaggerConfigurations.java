package br.com.tivia.config.swagger;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.tivia.utils.HostPropertiesUtil;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Thiago Cavalcanti
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.tivia"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para Token JWT")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("API Tivia Avaliação REST")
                .description("<h2>Visão Geral</h2>"
                        + "<h2>Autenticação</h2>"
                        + "<p>Não é necessária uma autenticação para enviar requisições a esta API.</p>"
                        + "<h2>Códigos de Erro</h2>"
                        + "<p><strong>200 -</strong><em> Sucesso:</em> A requisição ocorreu como esperado e realizou a operação.</p>"
                        + "<p><strong>500 -</strong><em> Internal Server Error:</em> Está sendo usado genericamente para erros em geral.</p>"
                        + "<p><strong>400 -</strong><em> Bad Request:</em> Dados da requisição inválidos.</p>"
                        + "<p><strong>401 -</strong><em> Unauthorized:</em> Token inválido, erro no login.</p>"
                        + "<p><strong>404 -</strong><em> Not Found:</em> Documento Fiscal não encontrado.</p>")
                .version(HostPropertiesUtil.getPropVersionToApiInfo())
                .contact(new Contact("Suporte Tivia", "http://www.tiviati.com.br/", null))
                .build();

        return apiInfo;
    }

}
