
package br.com.saulo.user.exception;

public class BadRequestException extends ExceptionOrder{

     private static final long serialVersionUID = -8570200930094282480L;
     

     public BadRequestException(String key, String msg){
          
          super(key, msg);
          
     }

     public BadRequestException(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
          
          super(globalRegistroNaoEncontrado);

     }

}
