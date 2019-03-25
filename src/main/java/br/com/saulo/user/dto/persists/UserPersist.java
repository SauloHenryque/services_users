package br.com.saulo.user.dto.persists;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User Persist")
public class UserPersist implements Serializable {
	
	private static final long serialVersionUID = -7329881749188627883L;
	
    /**
     * Nome user
     */
    @ApiModelProperty(value = "Nome do User", position = 1)
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;
    
    /**
     * Email
     */
    @ApiModelProperty(value = "Email User", position = 2)
    @NotNull(message = "Email não pode ser nulo")
    @Email()
    private String email;
    
    /**
     * Email
     */
    @ApiModelProperty(value = "Passord User", position = 3)
    @NotNull(message = "Password não pode ser nulo")
    private String password;

}
