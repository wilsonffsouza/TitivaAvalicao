package br.com.tivia.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Thiago Cavalcanti
 */
@Getter
@Setter
public class LoginForm {

    @ApiModelProperty(notes = "Email do usuário", required = true, example = "user@email.com")
    @Size(min = 7, max = 100)
    @Email
    @NotBlank
    private String email;

    @ApiModelProperty(notes = "Senha do usuário", required = true, example = "abc1234")
    @Size(min = 3, max = 100)
    @NotBlank
    private String senha;

}
