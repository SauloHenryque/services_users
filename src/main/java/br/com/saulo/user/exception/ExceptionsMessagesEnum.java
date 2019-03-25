
package br.com.saulo.user.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.saulo.user.ultil.I18n;
import br.com.twsoftware.alfred.object.Objeto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ExceptionsMessagesEnum {


	REGISTRO_NAO_ENCONTRADO(NOT_FOUND.value(), "registro_nao_encontrado", NotFoundException.class),
	
	EMAIL_JA_CADASTRADO(BAD_REQUEST.value(), "email_ja_cadastrado", BadRequestException.class)
	

	;
	
    @Getter
    private Integer codigo;

    @Getter
    private String key;

    @Getter
    private Class<? extends ExceptionOrder> klass;

    ExceptionsMessagesEnum(int codigo, String key, Class<? extends ExceptionOrder> klass) {

        this.key = key;
        this.klass = klass;
        this.codigo = codigo;

    }

    public static ExceptionsMessagesEnum getEnum(String key) {

        for (ExceptionsMessagesEnum e : ExceptionsMessagesEnum.values()) {

            if (e.getKey().equals(key)) {
                return e;
            }

        }

        return null;

    }

    public String getMessage() {

        return I18n.getMessage(this.key);

    }

    public void raise() {

        log.debug("Raising error: {}", this);
        raiseException(getMessage());

    }
    
    public void raiseWithCustomMessage(String customMessage)
    {   
        raiseException(customMessage);
    }

    private void raiseException(String msg) {

        if (this.badRequest()) {

            throw new BadRequestException(key, msg);

        } else if (this.notFound()) {

            throw new NotFoundException(key, msg);

        }

    }

    public void raise(String... textoDinamico) {

        String texto = getMessage();
        if (textoDinamico != null) {

            Integer count = 0;
            while (Objeto.notBlank(texto) && texto.contains("{}")) {

                if (textoDinamico.length == 1) {

                    texto = texto.replace("{}", (String) Objeto.or(textoDinamico[count], ""));
                    break;

                } else {

                    texto = texto.replaceFirst("\\{\\}", (String) Objeto.or(textoDinamico[count], ""));

                }
                count++;

            }

        }

        raiseException(texto);

    }

    public void raiseLogError(String... textoErro) {

        if (Objeto.notBlank(textoErro)) {

            for (String erro : textoErro) {
                log.error(erro);
            }
        }

        raise();
    }

    public void raiseLogInfo(String... textoErro) {

        if (Objeto.notBlank(textoErro)) {

            for (String erro : textoErro) {
                log.info(erro);
            }
        }

        raise();
    }

    public void raiseWithCustomMessageLogInfo(String customMessage, String... mensagemLogInfo) {

        if (Objeto.notBlank(mensagemLogInfo)) {

            for (String erro : mensagemLogInfo) {
                log.info(erro);
            }
        }

        raiseWithCustomMessage(customMessage);
    }

    private Boolean badRequest() {

        return this.codigo == BAD_REQUEST.value();
    }

    private Boolean notFound() {

        return this.codigo == NOT_FOUND.value();
    }
}
