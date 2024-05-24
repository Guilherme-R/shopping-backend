package gsobrinho.shoppingbackend.domain.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(){
        super("Bad request!");
    }

    public BusinessException(final String message){
        super(message);
    }
}
