package br.com.saulo.user.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saulo.user.dto.persists.UserPersist;
import br.com.saulo.user.dto.responses.UserResponse;
import br.com.saulo.user.entidades.UserEntidade;
import br.com.saulo.user.servicos.UserServico;
import br.com.saulo.user.ultil.GenericConvert;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "usuario")
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
	
	 	@Autowired
		private UserServico userServico;
		
	 	@PostMapping
	    public ResponseEntity<?> salvar(@RequestBody @Valid UserPersist request) {

			UserEntidade userEntidade = GenericConvert.convertModelMapper(request, UserEntidade.class);
			userEntidade 				= userServico.salvarUser(userEntidade);
			UserResponse response 		= GenericConvert.convertModelMapper(userEntidade, UserResponse.class);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

		@PutMapping("/{id}")
	    public ResponseEntity<?> atualizar(@PathVariable("id") long id, @RequestBody @Valid UserPersist request ) {
			
			UserEntidade userEntidade 	= GenericConvert.convertModelMapper(request, UserEntidade.class);
			userEntidade.setId(id);
			
			userEntidade 				= userServico.atualizarUser(userEntidade);
			UserResponse response 		= GenericConvert.convertModelMapper(userEntidade, UserResponse.class);

			return ResponseEntity.status(HttpStatus.OK).body(response);
	    }


}
