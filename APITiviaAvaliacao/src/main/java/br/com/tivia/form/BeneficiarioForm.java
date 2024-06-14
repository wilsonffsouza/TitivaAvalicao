package br.com.tivia.form;

import br.com.tivia.model.Beneficiario;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

/**
 *
 * @author Wilson.Souza
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioForm {
    
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

    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "yyyy-MM-dd")
    @NotNull
    @NotBlank
    private String dataNascimento;
    
    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "yyyy-MM-dd HH:mm:ss")
    private String data_created;
    
    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "yyyy-MM-dd HH:mm:ss")

    private String data_updated;

    public BeneficiarioForm(Beneficiario b) {
        this.id = b.getId();
        this.nome = b.getNome();
        this.telefone = b.getTelefone();
        this.dataNascimento = b.getData_nascimento();
        this.data_created = b.getData_created();
        this.data_updated = b.getData_updated();
    }
    
    public Beneficiario converter() {
        Beneficiario temp = new Beneficiario();
        temp.setId(this.id);
        temp.setNome(this.nome);
        temp.setTelefone(this.telefone);
        temp.setData_nascimento(dataNascimento);
        temp.setData_created(this.data_created);
        temp.setData_updated(this.data_created);
        return temp;
    }
    
    public static List<BeneficiarioForm> converter(List<Beneficiario> lista) {
        List<BeneficiarioForm> list = new ArrayList<>();
        if (lista != null) {
            for (Beneficiario b : lista) {
                list.add(new BeneficiarioForm(b));
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
