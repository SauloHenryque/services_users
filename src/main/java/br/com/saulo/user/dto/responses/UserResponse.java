package br.com.saulo.user.dto.responses;


import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Response User")
public class UserResponse implements Serializable{

	private static final long serialVersionUID = 4192610937139207457L;
	
	/**
     * Código de Identificação.
     */
    @ApiModelProperty(value = "Identificação da User", position = 1)
    private Long id;

    /**
     * User da user.
     */
    @ApiModelProperty(value = "Nome da User", position = 2)
    private String nome;
    
    /**
     * Response email
     */
    @ApiModelProperty(value = "Email da User", position = 3)
    private String email;
    
    /**
     * Response password
     */
    @ApiModelProperty(value = "Password da User", position = 4)
    private String password;
}
