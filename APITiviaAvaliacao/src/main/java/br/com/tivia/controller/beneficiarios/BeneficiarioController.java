package br.com.tivia.controller.beneficiarios;

import br.com.tivia.dto.ResponseDTO;
import br.com.tivia.enums.TipoRetorno;
import br.com.tivia.form.BeneficiarioForm;
import br.com.tivia.service.BeneficiarioServices;
import br.com.tivia.utils.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Wilson.Souza
 */
@Controller
@RequestMapping("/beneficiario")
@Api(tags = "Beneficiario", description = "Operações referentes a beneficiarios")
public class BeneficiarioController {

    @ApiOperation(value = "Criar cadastro Beneficiarios ", notes = "Cadastro de um beneficiario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Beneficiario criado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDTO> create(@RequestBody BeneficiarioForm form) {
        Log.logDiario("Inicio da requisicao criar Beneficiario");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {

            TipoRetorno tipoRetorno = BeneficiarioServices.save(form);
            if (tipoRetorno.equals(TipoRetorno.SOCESSO)) {
                dto = ResponseDTO.createSucess(tipoRetorno);
                resposta = ResponseEntity.ok(dto);
            } else {
                dto = ResponseDTO.falha(tipoRetorno);
                resposta = ResponseEntity.badRequest().body(dto.toJson2());
            }

        } catch (Exception e) {
            resposta = ResponseEntity.internalServerError().body(ResponseDTO.falhaNaIntegracao().toJson());
        }

        Log.logDiario("Fim da requisicao criar Beneficiario com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Atualizar cadastro Beneficiarios", notes = "Atualizar cadastro Beneficiarios", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Beneficiario atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ResponseDTO> update(@RequestBody BeneficiarioForm form) {
        Log.logDiario("Inicio da requisicao Atualizar Beneficiario");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {
            TipoRetorno tipoRetorno = BeneficiarioServices.update(form);
            if (tipoRetorno.equals(TipoRetorno.SOCESSO)) {
                dto = ResponseDTO.updateSucess(tipoRetorno);
                resposta = ResponseEntity.ok(dto);
            } else {
                dto = ResponseDTO.falha(tipoRetorno);
                resposta = ResponseEntity.badRequest().body(dto.toJson2());
            }
        } catch (Exception e) {
            resposta = ResponseEntity.internalServerError().body(ResponseDTO.falhaNaIntegracao().toJson());
        }

        Log.logDiario("Fim da requisicao Atualizar Beneficiario com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Excluir cadastro Beneficiario", notes = "Remove um cadastro de beneficiario", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Beneficiario excluído com sucesso"),
        @ApiResponse(code = 400, message = "Id inválido"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<ResponseDTO> delete(@RequestParam Long id) {
        Log.logDiario("Inicio da requisicao Excluir Beneficiario");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {
            TipoRetorno tipoRetorno = BeneficiarioServices.delete(id);
            if (tipoRetorno.equals(TipoRetorno.SOCESSO)) {
                dto = ResponseDTO.deleteSucess(tipoRetorno);
                resposta = ResponseEntity.ok(dto);
            } else {
                dto = ResponseDTO.falha(tipoRetorno);
                resposta = ResponseEntity.badRequest().body(dto.toJson2());
            }

        } catch (Exception e) {
            resposta = ResponseEntity.internalServerError().body(ResponseDTO.falhaNaIntegracao().toJson());
        }

        Log.logDiario("Fim da requisicao Excluir Beneficiario com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Listar beneficiarios", notes = "Lista os beneficiario cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista beneficiarios obtidos com sucesso"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<BeneficiarioForm>> findAll(HttpServletResponse response) {
        Log.logDiario("Inicio da requisicao Listar Beneficiario");
        List<BeneficiarioForm> retorno = null;
        try {
            retorno = BeneficiarioServices.findAll();
        } catch (Exception e) {
            response = ResponseDTO.retornarResponse500(response);
        }
        Log.logDiario("Fim da requisicao Listar Beneficiario");
        return new ResponseEntity(retorno, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar Beneficiario", notes = "Lista beneficiario especifico", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Beneficiario obtido com sucesso"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BeneficiarioForm> find(@RequestParam Long id) {
        Log.logDiario("Inicio da requisicao Listar Beneficiario");
        ResponseEntity resposta = null;
        String retorno = null; 
        if(id >= 0) {
            try {
                retorno = BeneficiarioServices.find(id);
                if (retorno != null) {
                    resposta = new ResponseEntity(retorno, HttpStatus.OK);
                } else {
                    resposta = new ResponseEntity(ResponseDTO.falha(TipoRetorno.SEM_DADOS_RETORNO).toJson2(), HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                resposta = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resposta = new ResponseEntity(ResponseDTO.falha(TipoRetorno.PARAMETRO_INVALIDO).toJson2(), HttpStatus.BAD_REQUEST);
        }
        Log.logDiario("Fim da requisicao Listar Beneficiario");
        return resposta;
    }

}
