package gsobrinho.shoppingbackend.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gsobrinho.shoppingbackend.domain.exception.BusinessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String MSG_ERROR = "Unable to read json!";

    public static <T> T jsonToObject(final String json, TypeReference<T> valueTypeRef) {
        try{
            return objectMapper.readValue(json, valueTypeRef);
        }catch(JsonProcessingException e){
            log.error(MSG_ERROR);
            throw new BusinessException(MSG_ERROR);
        }
    }

    public static <T> T fileToObject(final String pathName, TypeReference<T> valueTypeRef) {
        try{
            return objectMapper.readValue(new File(pathName), valueTypeRef);
        }catch(IOException e){
            log.error(e.getMessage());
            throw new BusinessException(MSG_ERROR);
        }
    }

    public static String fileToObject(final Object obj) {
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(IOException e){
            log.error(MSG_ERROR);
            throw new BusinessException(MSG_ERROR);
        }
    }
}
