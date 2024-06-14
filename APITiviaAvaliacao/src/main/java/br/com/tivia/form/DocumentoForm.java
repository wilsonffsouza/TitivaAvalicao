package br.com.tivia.form;

import br.com.tivia.model.Documentos;
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
public class DocumentoForm {
    
    @ApiModelProperty(notes = "Identificador do cadastro ", required = true, example = "1")
    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "2147483647", inclusive = true)
    private Long id;
    
    @ApiModelProperty(notes = "Identificador do vinculo com o Beneficiario", required = true, example = "1")
    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "2147483647", inclusive = true)
    private Long beneficiario_id;
    
    @ApiModelProperty(notes = "Tipo do documento", required = true, example = "RG")
    @Size(min = 1, max = 20)
    @NotBlank
    @NotNull
    private String tipo_documento;

    @ApiModelProperty(notes = "Descricao do documento", required = true, example = "")
    @NotNull
    @NotBlank
    private String descricao;
    
    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "yyyy-MM-dd HH:mm:ss")
    private String data_created;
    
    @ApiModelProperty(notes = "Data de nascimento do beneficiario", required = true, example = "yyyy-MM-dd HH:mm:ss")
    private String data_updated;
    
    public DocumentoForm(Documentos d) {
        this.id = d.getId();
        this.beneficiario_id = d.getBeneficiario_id();
        this.tipo_documento = d.getTipo_documento();
        this.descricao = d.getDescricao();
        this.data_created = d.getData_created();
        this.data_updated = d.getData_updated();
    }

    public Documentos converter() {
        Documentos temp = new Documentos();
        temp.setId(this.id);
        temp.setBeneficiario_id(this.beneficiario_id);
        temp.setTipo_documento(this.tipo_documento);
        temp.setDescricao(this.descricao);
        temp.setData_created(this.data_created);
        temp.setData_updated(this.data_updated);
        return temp;
    }
    
    public static List<DocumentoForm> converter(ArrayList<Documentos> lista) {
        List<DocumentoForm> list = new ArrayList<>();
        if (lista != null) {
            for (Documentos b : lista) {
                list.add(new DocumentoForm(b));
            }
        }
        return list;
    }
    
    public static String toJson(Documentos d){
        JSONObject json = new JSONObject();
        json.put("id", d.getId());
        json.put("Beneficiario_id", d.getBeneficiario_id());
        json.put("tipo_documento", d.getTipo_documento());
        json.put("descricao", d.getDescricao());
        return json.toString();
    }
}
