package br.com.saulo.user.servicos;

import static br.com.saulo.user.exception.ExceptionOrder.checkThrow;
import static br.com.saulo.user.exception.ExceptionsMessagesEnum.EMAIL_JA_CADASTRADO;
import static br.com.saulo.user.exception.ExceptionsMessagesEnum.REGISTRO_NAO_ENCONTRADO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.saulo.user.entidades.UserEntidade;
import br.com.saulo.user.repositorios.UserRepositorio;
import lombok.Data;

@Data
@Service
public class UserServico {
	
    
    @Autowired
    private UserRepositorio storeRepositorio;
	

	/**
	 * Método responsável pela criação da user.
	 *  
	 * @param userEntidade <br/>
	 *                   Objeto que representa a requisição
	 *                   {@link userEntidade}
	 * 
	 * @return {@link UserEntidade}
	 */
	public UserEntidade salvarUser(UserEntidade storeEntidade) {
		
		checkThrow(storeRepositorio.existsByEmail(storeEntidade.getEmail()), EMAIL_JA_CADASTRADO);
        return storeRepositorio.save(storeEntidade);

	}
	
	
	/**
	 * Método responsável pela edição da user.
	 *  
	 * @param userEntidade <br/>
	 *                   Objeto que representa a requisição
	 *                   {@link userEntidade}
	 * 
	 * @return {@link UserEntidade}
	 */
	public UserEntidade atualizarUser(UserEntidade storeEntidade) {
		
		
		checkThrow(!storeRepositorio.existsById(storeEntidade.getId()), REGISTRO_NAO_ENCONTRADO);
		checkThrow(storeRepositorio.existsByEmailAndIdNotIn(storeEntidade.getNome(), storeEntidade.getId()), EMAIL_JA_CADASTRADO);
		 
		return storeRepositorio.save(storeEntidade);
	}


}
