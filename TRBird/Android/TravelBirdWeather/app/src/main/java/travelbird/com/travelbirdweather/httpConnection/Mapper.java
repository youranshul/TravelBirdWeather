package travelbird.com.travelbirdweather.httpConnection;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Singleton wrapper class which configures the Jackson JSON parser.
 */
public final class Mapper
{
    private static ObjectMapper MAPPER;

    public static ObjectMapper get()
    {
        if (MAPPER == null)
        {
            MAPPER = new ObjectMapper();
            MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        }

        return MAPPER;
    }

    public static String string(Object data)
    {
        try
        {
            return get().writeValueAsString(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T objectOrThrow(String data, Class<T> type) throws JsonParseException, JsonMappingException, IOException
    {
        return get().readValue(data, type);
    }

    public static <T> T object(String data, Class<T> type)
    {
        try
        {
            return objectOrThrow(data, type);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}