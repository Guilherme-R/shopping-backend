package gsobrinho.shoppingbackend.domain.exception;

import gsobrinho.shoppingbackend.core.utils.StringUtils;

public class BusinessException extends RuntimeException{

    public BusinessException(){
        super(StringUtils.getMessage("exception.business.standardMessage"));
    }

    public BusinessException(final String message, final Object ...params){
        super(StringUtils.getMessage(message, params));
    }

    public BusinessException(final String message, final Throwable cause, final Object ...params) {
        super(StringUtils.getMessage(message, params), cause);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }
}
