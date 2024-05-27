package gsobrinho.shoppingbackend.core.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static final String BLANK = "";

    private static final MessageSource messageSource;

    static{
        ReloadableResourceBundleMessageSource bundleMessageSource
                = new ReloadableResourceBundleMessageSource();

        bundleMessageSource.setBasename("classpath:messages");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        messageSource = bundleMessageSource;
    }

    public static String getMessage(final String key, final Object ...params){
        return messageSource.getMessage(key, params, key, LocaleContextHolder.getLocale());
    }
}
