
package br.com.saulo.user.exception;

import lombok.Getter;

public class ExceptionOrder extends RuntimeException {

     private static final long serialVersionUID = -601362897548358062L;

     @Getter
     private ExceptionsMessagesEnum msgEnum;

     private String key;

     public ExceptionOrder(String key, String msg) {

          super(msg);
          this.key = key;
          this.msgEnum = ExceptionsMessagesEnum.getEnum(key);
     }
     
     public ExceptionOrder(ExceptionsMessagesEnum globalRegistroNaoEncontrado) {
          
          super(globalRegistroNaoEncontrado.getMessage());
          this.msgEnum = globalRegistroNaoEncontrado;
          
     }
     
     public static void checkThrow(boolean expression, ExceptionsMessagesEnum exceptionsMessagesPIEREnum) throws ExceptionOrder {

          if (expression) {
               exceptionsMessagesPIEREnum.raise();
          }
          
     }

     public static void checkThrow(boolean expression, ExceptionsMessagesEnum exceptionsMessagesPIEREnum, String... textoDinamico) throws ExceptionOrder {

          if (expression) {
               exceptionsMessagesPIEREnum.raise(textoDinamico);
          }
          
     }

     public static void checkThrowLogError(boolean expression, ExceptionsMessagesEnum exceptionsMessagesPIEREnum, String... textoLogErro) throws ExceptionOrder {

          if (expression) {
               exceptionsMessagesPIEREnum.raiseLogError(textoLogErro);
          }
          
     }

     public static void checkThrowLogInfo(boolean expression, ExceptionsMessagesEnum exceptionsMessagesPIEREnum, String... textoLogErro) throws ExceptionOrder {

          if (expression) {
               exceptionsMessagesPIEREnum.raiseLogInfo(textoLogErro);
          }
          
     }

}
