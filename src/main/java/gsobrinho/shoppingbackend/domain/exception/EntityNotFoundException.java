package gsobrinho.shoppingbackend.domain.exception;

import gsobrinho.shoppingbackend.core.utils.StringUtils;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(){
        super(StringUtils.getMessage("exception.entityNotFound.standardMessage"));
    }

    public EntityNotFoundException(final String message, final Object ...params){
        super(message, params);
    }

    public EntityNotFoundException(final String message, final Throwable cause, final Object ...params) {
        super(message, params, cause);
    }

    public EntityNotFoundException(final Throwable cause) {
        super(cause);
    }
}
