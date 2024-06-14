package br.com.tivia.controller.validation;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Validações" , description = "Operações referente a Validações")
public class HealthCheckController
{
    @GetMapping("/healthcheck") 
    public String healthCheck() 
    { 
	return "OK"; 
    } 
}
