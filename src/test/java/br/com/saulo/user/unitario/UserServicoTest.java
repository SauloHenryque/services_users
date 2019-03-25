package br.com.saulo.user.unitario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.saulo.user.entidades.UserEntidade;
import br.com.saulo.user.exception.BadRequestException;
import br.com.saulo.user.exception.NotFoundException;
import br.com.saulo.user.repositorios.UserRepositorio;
import br.com.saulo.user.servicos.UserServico;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class UserServicoTest {
 
	 @InjectMocks
     private UserServico userServicoMock;
	
     @Mock
     private UserRepositorio userRepositorio;
     
     @Mock
     private UserEntidade userEntidade;
     

     @Before
     public void setup() {

          MockitoAnnotations.initMocks(this);
     }
     
     @Test
     public void salvarUser(){
    	 
    	 UserEntidade	userEntidadeNovo		 = new UserEntidade();
    	 
    	 userEntidadeNovo.setNome("Nome Teste");
    	 userEntidadeNovo.setEmail("email@teste.com");
    	 userEntidadeNovo.setPassword("13321344654");
    	 
         Mockito.when(userRepositorio.existsByEmail("Teste")).thenReturn(false);
         Mockito.when(userRepositorio.save(Mockito.any(UserEntidade.class))).thenReturn(userEntidadeNovo);

         UserEntidade userEntidadeCadastrado = userServicoMock.salvarUser(userEntidadeNovo);

         testCase(userEntidadeNovo, userEntidadeCadastrado);
     }
     
     @Test(expected = BadRequestException.class)
     public void salvarUserEmailRepetido(){
    	 
    	 UserEntidade	userEntidadeNovo		 = new UserEntidade();
    	 
    	 userEntidadeNovo.setNome("Nome Teste");
    	 userEntidadeNovo.setEmail("email@teste.com");
    	 userEntidadeNovo.setPassword("13321344654");
    	 
         Mockito.when(userRepositorio.existsByEmail("email@teste.com")).thenReturn(true);
         Mockito.when(userRepositorio.save(Mockito.any(UserEntidade.class))).thenReturn(userEntidadeNovo);

         userServicoMock.salvarUser(userEntidadeNovo);
     }
     
     
    @Test
    public void atualizarUser(){
    	 
    	 UserEntidade	userEntidadeNovo		 = new UserEntidade();
    	 
    	 userEntidadeNovo.setId(1L);
    	 userEntidadeNovo.setNome("Nome Teste");
    	 userEntidadeNovo.setEmail("email@teste.com");
    	 userEntidadeNovo.setPassword("13321344654");
    	 
    	 Mockito.when(userRepositorio.existsById(1L)).thenReturn(true);
         Mockito.when(userRepositorio.existsByEmailAndIdNotIn("email@teste.com", 1L)).thenReturn(false);
         Mockito.when(userRepositorio.save(Mockito.any(UserEntidade.class))).thenReturn(userEntidadeNovo);

         UserEntidade userEntidadeAtualizado = userServicoMock.atualizarUser(userEntidadeNovo);

         testCase(userEntidadeNovo, userEntidadeAtualizado);
         TestCase.assertEquals(userEntidadeNovo.getId(), userEntidadeAtualizado.getId());
    }
    
    @Test(expected = NotFoundException.class)
    public void atualizarUserNaoEncontrado(){
   	 
   	 	UserEntidade	userEntidadeNovo		 = new UserEntidade();
   	 
   	 	userEntidadeNovo.setId(1L);
   	 	userEntidadeNovo.setNome("Nome Teste");
   	 	userEntidadeNovo.setEmail("email@teste.com");
   	 	userEntidadeNovo.setPassword("13321344654");
   	 
   	 	Mockito.when(userRepositorio.existsById(1L)).thenReturn(false);
   	 	Mockito.when(userRepositorio.existsByEmailAndIdNotIn("email@teste.com", 1L)).thenReturn(false);
   	 	Mockito.when(userRepositorio.save(Mockito.any(UserEntidade.class))).thenReturn(userEntidadeNovo);

   	 	userServicoMock.atualizarUser(userEntidadeNovo);
    }
    
    @Test(expected = BadRequestException.class)
    public void atualizarUserNomeRepetido(){
   	 
   	 	UserEntidade	userEntidadeNovo		 = new UserEntidade();
   	 
   	 	userEntidadeNovo.setId(1L);
   	 	userEntidadeNovo.setNome("Nome Teste");
   	 	userEntidadeNovo.setEmail("email@teste.com");
   	 	userEntidadeNovo.setPassword("13321344654");
   	 
   	 	Mockito.when(userRepositorio.existsById(1L)).thenReturn(true);
   	 	Mockito.when(userRepositorio.existsByEmailAndIdNotIn("email@teste.com", 1L)).thenReturn(true);
   	 	Mockito.when(userRepositorio.save(Mockito.any(UserEntidade.class))).thenReturn(userEntidadeNovo);

   	 	userServicoMock.atualizarUser(userEntidadeNovo);
    }
    
    
	private static void testCase(UserEntidade userEntidade, UserEntidade userEntidadeCadastrado) {
         
         TestCase.assertNotNull(userEntidadeCadastrado);
         TestCase.assertEquals(userEntidade.getNome(), userEntidadeCadastrado.getNome());
         TestCase.assertEquals(userEntidade.getEmail(), userEntidadeCadastrado.getEmail());
         TestCase.assertEquals(userEntidade.getPassword(), userEntidadeCadastrado.getPassword());
         
    }
}
