package br.com.tivia.controller.documentos;

import br.com.tivia.dto.ResponseDTO;
import br.com.tivia.enums.TipoRetorno;
import br.com.tivia.form.DocumentoForm;
import br.com.tivia.service.DocumentoServices;
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
@RequestMapping("/documentos")
@Api(tags = "Documentos", description = "Operações referentes a Documentos")
public class DocumentoController {

    @ApiOperation(value = "Criar cadastro Documentos ", notes = "Cadastro de um Documentos", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos criado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDTO> create(@RequestBody DocumentoForm form) {
        Log.logDiario("Inicio da requisicao criar Documentos");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {

            TipoRetorno tipoRetorno = DocumentoServices.save(form);
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

        Log.logDiario("Fim da requisicao criar Documentos com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Atualizar cadastro Documentos", notes = "Atualizar cadastro Documentos", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ResponseDTO> update(@RequestBody DocumentoForm form) {
        Log.logDiario("Inicio da requisicao Atualizar Documentos");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {
            TipoRetorno tipoRetorno = DocumentoServices.update(form);
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

        Log.logDiario("Fim da requisicao Atualizar Documentos com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Excluir cadastro Documentos", notes = "Remove um cadastro de Documentos", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos excluído com sucesso"),
        @ApiResponse(code = 400, message = "Id inválido"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<ResponseDTO> delete(@RequestParam Long id) {
        Log.logDiario("Inicio da requisicao Excluir Documentos");
        ResponseEntity resposta;
        ResponseDTO dto;
        try {
            TipoRetorno tipoRetorno = DocumentoServices.delete(id);
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

        Log.logDiario("Fim da requisicao Excluir Documentos com status " + resposta.getStatusCode());
        return resposta;
    }

    @ApiOperation(value = "Listar Documentos", notes = "Lista os Documentos cadastrados", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista Documentos obtidos com sucesso"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DocumentoForm>> findAll(HttpServletResponse response) {
        Log.logDiario("Inicio da requisicao Listar Documentos");
        List<DocumentoForm> retorno = null;
        try {
            retorno = DocumentoServices.findAll();
        } catch (Exception e) {
            response = ResponseDTO.retornarResponse500(response);
        }
        Log.logDiario("Fim da requisicao Listar Documentos");
        return new ResponseEntity(retorno, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar Documentos", notes = "Lista Documentos especifico", authorizations = {
        @Authorization(value = "jwtToken")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos obtido com sucesso"),
        @ApiResponse(code = 500, message = "Falha na integração")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<DocumentoForm> find(@RequestParam Long id) {
        Log.logDiario("Inicio da requisicao Listar Documentos");
        ResponseEntity resposta = null;
        String retorno = null; 
        if(id >= 0) {
            try {
                retorno = DocumentoServices.find(id);
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
        Log.logDiario("Fim da requisicao Listar Documentos");
        return resposta;
    }

}
