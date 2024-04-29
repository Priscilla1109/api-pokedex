package com.api.pokedex.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

public class JsonUtil {

    public static <T> T loadCollection(final String filePath, final CollectionType collectionType, final ObjectMapper objectMapper) throws IOException {
        String jsonValue = new String(Files.readAllBytes(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filePath).toPath()));
        return objectMapper.readValue(jsonValue, collectionType);
    }

    public static <T> T loadObject(final String filePath, final Class<T> clazz, final ObjectMapper objectMapper) throws IOException {
        String jsonValue = new String(Files.readAllBytes(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filePath).toPath()));
        return objectMapper.readValue(jsonValue, clazz);
    }

}
