package gsobrinho.shoppingbackend.domain.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(){
        super("Entity not found!");
    }

    public EntityNotFoundException(final String message){
        super(message);
    }
}
