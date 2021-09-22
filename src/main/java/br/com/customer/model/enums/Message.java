package br.com.customer.model.enums;

import br.com.customer.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    CUSTOMER_NOT_FOUND("Cliente não encontrado", HttpStatus.NOT_FOUND),
    ILLEGAL_ARGUMENT_EXCEPTION("Argumento informado não é válido.", HttpStatus.BAD_REQUEST),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("Media type informado não é suportado", HttpStatus.BAD_REQUEST),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("Metodo HTTP não suportado", HttpStatus.BAD_REQUEST),
    CPF_ALREADY_EXISTS("CPF já cadastrado!", HttpStatus.CONFLICT);

    private String message;

    private HttpStatus statusCode;

    private Message(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public final String getMessage() {
        return this.message;
    }

    protected void setMessage(String value) {
        this.message = value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    protected void setStatus(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus()).message(this.getMessage()).build();
    }
}
