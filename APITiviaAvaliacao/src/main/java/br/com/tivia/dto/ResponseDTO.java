package br.com.tivia.dto;

import br.com.tivia.enums.TipoRetorno;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Wilson.Souza
 *
 */
@Getter
@Setter
// Objeto destinado à detalhar respostas das requisições
public class ResponseDTO {

    @ApiModelProperty(notes = "Titulo", example = "Falha na autenticação")
    private String titulo;
    @ApiModelProperty(notes = "Mensagem", example = "Token inválido")
    private String mensagem;
    @ApiModelProperty(notes = "Código HTTP", example = "401")
    private int codigo;

    public ResponseDTO(String titulo, String mensagem, int codigo) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("Codigo HTTP", codigo);
        json.put("Titulo", titulo);
        json.put("Mensagem", mensagem);
        return json.toString();
    }
    
    public String toJson2() {
        JSONObject json = new JSONObject();
        json.put("Titulo", titulo);
        json.put("Mensagem", mensagem);
        json.put("CodigoAPI", codigo);
        return json.toString();
    }
    
    public static ResponseDTO createSucess(TipoRetorno tr) {
        return new ResponseDTO("Sucesso", tr.getMensagem(), tr.getCodigo());
    }
    
    public static ResponseDTO updateSucess(TipoRetorno tr) {
        String mensagem = "Atualização realizada com sucesso";
        return new ResponseDTO("Sucesso", mensagem, tr.getCodigo());
    }

    
     public static ResponseDTO deleteSucess(TipoRetorno tr) {
        String mensagem = "Exclusão realizada com sucesso";
        return new ResponseDTO("Sucesso", mensagem, tr.getCodigo());
    }

    public static ResponseDTO falha(TipoRetorno tr) {
        return new ResponseDTO("Falha", tr.getMensagem(), tr.getCodigo());
    }
    public static ResponseDTO falhaNaIntegracao() {
        return new ResponseDTO("Falha na integração.",
                "Um alerta já foi enviado para o Administrador dessa API.", 500);
    }

    public static HttpServletResponse retornarResponse500(HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("ASCII");
        try {
            response.getOutputStream().println(ResponseDTO.falhaNaIntegracao().toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
