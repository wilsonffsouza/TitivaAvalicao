package br.com.tivia.dto;

import br.com.tivia.form.*;
import br.com.tivia.model.Beneficiario;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.json.JSONObject;

/**
 *
 * @author Wilson.Souza
 */
@Getter
public class BeneficiarioDTO {
    
    @ApiModelProperty(notes = "Identificador do cadastro ", required = true, example = "1")
    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "2147483647", inclusive = true)
    private Long id;
    
    @ApiModelProperty(notes = "Nome do beneficiario", required = true, example = "João")
    @Size(min = 1, max = 20)
    @NotBlank
    private String nome;

    @ApiModelProperty(notes = "Telefone para contato", required = true, example = "(xx)xxxx-xxxx ou (xx)xxxxx-xxxx")
    @NotNull
    @NotBlank
    private String telefone;

    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "dd/MM/yyyy")
    @NotNull
    @NotBlank
    private String dataNascimento;

    public BeneficiarioDTO(Beneficiario b) {
        this.nome = b.getNome();
        this.telefone = b.getTelefone();
        this.dataNascimento = b.getData_nascimento();
    }
    
    public Beneficiario converter() {
        Beneficiario temp = new Beneficiario();
        temp.setNome(this.nome);
        temp.setTelefone(this.telefone);
        temp.setData_nascimento(dataNascimento);
        return temp;
    }
    
    public static List<BeneficiarioDTO> converter(List<Beneficiario> lista) {
        List<BeneficiarioDTO> list = new ArrayList<>();
        if (lista != null) {
            for (Beneficiario b : lista) {
                list.add(new BeneficiarioDTO(b));
            }
        }
        return list;
    }
    
    public static String toJson(Beneficiario b){
        JSONObject json = new JSONObject();
        json.put("id", b.getId());
        json.put("nome", b.getNome());
        json.put("telefone", b.getTelefone());
        json.put("dataNascimento", b.getData_nascimento());
        json.put("dataCreated", b.getData_created());
        json.put("dataUpdated", b.getData_updated());
        return json.toString();
    }
}
